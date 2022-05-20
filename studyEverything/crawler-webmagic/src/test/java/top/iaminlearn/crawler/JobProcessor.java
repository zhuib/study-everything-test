package top.iaminlearn.crawler;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Date: 2021/5/4 17:22
 */
public class JobProcessor implements PageProcessor {
    // 解析页面
    @Override
    public void process(Page page) {
        // 默认在控制台输出
        // 解析返回的数据page 并且把解析的结果放到ResultItems中
        // css 选择器
//        page.putField("div",page.getHtml().css("ol.slogen li.item fore1").all());
        // xpath  /html/body/div[6]/div[1]/div[2]/div/div[2]/div[2]/div[2]/div[2]/span
        page.putField("div1",page.getHtml().xpath("//*[@id=\"爬虫设置-site\"]").regex("爬.*e"));
        // 正则
//        page.putField("div2",page.getHtml().css("div#news_id a").regex(".*江苏.*").all());
        // 获取第一条数据
//        page.putField("div3",page.getHtml().css("div#news_id a").regex(".*江苏.*").get());
//        page.putField("div4",page.getHtml().css("div#news_id a").regex(".*江苏.*").toString());

//        获取连接
//        page.addTargetRequests(page.getHtml().css("div#news_id").links().regex(".*9$").all());
//        page.putField("div5",page.getHtml().xpath("//*[@id='quanlist']/div[2]/div[2]/div[2]/span").all()); // 在上面的链接中去请求并获的title

    }

    private Site site = Site.me()
            .setCharset("utf8")   // 设置编码
            .setTimeOut(10000)  // 设置超时时间，ms
            .setRetrySleepTime(3000)  // 设置重试的时间间隔
            .setSleepTime(3);  // 设置重试次数

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new JobProcessor())
                .addUrl("https://www.ayulong.cn/blog/14")  // 设置爬取数据的页面
//                .addPipeline(new FilePipeline("C:\\Users\\zhuib\\Desktop\\result"))
                .thread(5)  // 多线程
                .run(); // 执行爬虫
    }
}
