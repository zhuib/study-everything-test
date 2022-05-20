package top.iaminlearn.crawler.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.iaminlearn.crawler.pojo.JobInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 2021/5/5 18:01
 */
@Component
public class JobProcessor implements PageProcessor {

    // 因为拿不到下一页的链接只好设置一个变量
    private int count = 1;
    private String url = "https://search.51job.com/list/030200,000000,0000,00,9,99,java,2,1.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=";
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-");
    private String today = formatter.format(new Date());
    @Override
    public void process(Page page) {
        // 因为在51招聘的页面中不能直接拿到页面元素, 经分析数据在页面的js中
        // 于是想办法将js对象(json数据)解析出来, 获取到详情页面的链接放到任务队列中
        // 解析页面, 获取script中招聘详情信息
        String dataJs = page.getHtml().css("script").regex(".*SEARCH_RESULT.*").get();
        // 判断获取到的页面是否为空
        if (!StringUtils.isEmpty(dataJs)) {
            System.out.println("开始抓取第" + count++ + "页");
            // 如果不为空, 表示这是列表页
            // 解析拿到json字符串
            dataJs = dataJs.substring(dataJs.indexOf("{"), dataJs.lastIndexOf("}") + 1);
            // 创建json对象
            JSONObject jsonObject = (JSONObject) JSONObject.parse(dataJs);
            // 根据分析拿到放置信息的数组
            JSONArray resArr = jsonObject.getJSONArray("engine_search_result");
            // 判断数组中是否存在数据
            if (resArr.size() > 0) {
                for (int i = 0; i < resArr.size(); i++) {
                    // 获取数组中的每一个对象
                    JSONObject resObj = (JSONObject) resArr.get(i);
                    //把获取到的url地址放到任务队列中
                    page.addTargetRequest(String.valueOf(resObj.get("job_href")));
                }
                // 获取下一页的url
                String bkUrl = "https://search.51job.com/list/030200,000000,0000,00,9,99,java,2," + (count + 1) + ".html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=";
                // 把url放到任务队列中
                page.addTargetRequest(bkUrl);
            } else {
                // 设置变量为初始值
                count = 0;
                // 如果没有数据那么爬虫结束
                return;
            }
        } else {
            // 如果为空, 表示这是招聘详情页, 解析页面, 获取招聘详情信息, 保存数据
            this.saveJobInfo(page);
        }

    }

    // 解析页面, 获取招聘详情信息, 保存数据
    private void saveJobInfo(Page page) {
        // 创建招聘详情对象
        JobInfo jobInfo = new JobInfo();

        // 解析页面
        Html html = page.getHtml();

        // 获取数据, 封装到对象中
        // 拿到有地址和发布时间的那条文字
        String desc = Jsoup.parse(html.css("p.msg.ltype").toString()).text();
        if (desc == null) {
            // 有极少数非51内部网站无法找到
            return;
        }
        // 为了能够找到发布时间截取了字符串
        desc = desc.substring(0, desc.lastIndexOf("发布"));
        // 设置公司名称
        jobInfo.setCompanyName(html.css("div.cn p.cname a", "text").toString().trim());
        // 设置公司地址
        jobInfo.setCompanyAddr(desc.substring(0, desc.indexOf("|")).trim());
        // 设置公司信息
        jobInfo.setCompanyInfo(Jsoup.parse(html.css("div.tmsg").toString()).text());
        // 设置职位名称
        jobInfo.setJobName(html.css("div.cn h1", "text").toString());
        // 设置工作地址
        jobInfo.setJobAddr(html.css("div.bmsg>p.fp", "text").toString());
        // 设置工作信息(要求)
        jobInfo.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
        // 设置当前链接
        jobInfo.setUrl(page.getUrl().toString());
        // 获取薪资
        // 有的没有写薪资, 先获取薪资的字符串
        String salaryText = html.css("div.cn strong", "text").toString();
        // 看看是否没有薪资这个字段
        if (!StringUtils.isEmpty(salaryText)) {
            // 使用工具类转换薪资字符串
            Integer[] salary = MathSalary.getSalary(salaryText);
            jobInfo.setSalaryMax(salary[0]);
            jobInfo.setSalaryMin(salary[1]);
        } else {
            // 没有则设为零
            jobInfo.setSalaryMax(0);
            jobInfo.setSalaryMin(0);
        }
        // 获取发布时间
        // 获取发布时间
        String time = desc.substring(desc.lastIndexOf("|") + 3);
        jobInfo.setTime(today + time.trim());
        // 把结果保存起来
        page.putField("jobInfo", jobInfo);
    }

    private Site site = Site.me()
            .setCharset("gbk") // 设置编码
            .setTimeOut(10*1000) // 设置超时时间
            .setRetrySleepTime(3000) // 设置重试的间隔时间
            .setRetryTimes(3); // 设置重试的次数

    @Override
    public Site getSite() {
        return site;
    }

    @Autowired
    private SpringDataPipeline springDataPipeline;

    // initialDelay当任务启动后, 等多久执行方法
    // fixedDelay每隔多久执行方法
//    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 1000)
    public void process() {
        Spider.create(new JobProcessor())
                .addUrl(url)
                // 设置Secheduler
                .setScheduler(new QueueScheduler()
                        // 设置Bloom去重
                        .setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(10)
                // 设置自定义的Pipeline储存数据
                .addPipeline(this.springDataPipeline)
                .run();
    }
}