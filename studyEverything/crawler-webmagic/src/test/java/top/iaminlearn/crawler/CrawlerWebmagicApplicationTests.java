package top.iaminlearn.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import top.iaminlearn.crawler.pojo.JobInfo;
import top.iaminlearn.crawler.pojo.JobInfoField;
import top.iaminlearn.crawler.service.JobInfoService;
import top.iaminlearn.crawler.service.JobRepositoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CrawlerWebmagicApplicationTests {


    @Autowired
    private JobInfoService jobInfoService;
    @Autowired
    private JobRepositoryService jobRepositoryService;
    //    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//    @Autowired
//    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 创建索引和映射
     */
    @Test
    public void createIndex() throws IOException {
        this.elasticsearchRestTemplate.createIndex(JobInfoField.class);
        this.elasticsearchRestTemplate.putMapping(JobInfoField.class);
    }


    @Test
    public void jobData() {
        //声明当前页码数
        int count = 1;
        //声明查询数据条数
        int pageSize = 0;

        //循环查询
        do {
            //从MySQL数据库中分页查询数据
            Page<JobInfo> page = this.jobInfoService.findAllPage(count, 500);

            //声明存放索引库数据的容器
            List<JobInfoField> list = new ArrayList<>();

            //遍历查询结果
            for (JobInfo jobInfo : page.getContent()) {
                //创建存放索引库数据的对象
                JobInfoField jobInfoField = new JobInfoField();
                //复制数据
                BeanUtils.copyProperties(jobInfo, jobInfoField);
                //把复制好的数据放到容器中
                list.add(jobInfoField);
            }

            //批量保存数据到索引库中
            this.jobRepositoryService.saveAll(list);

            //页面数加一
            count++;
            //获取查询数据条数
            pageSize = page.getContent().size();

        } while (pageSize == 500);
    }

//*****************************************************************************************************

    // 超时设置
    @Test
    void httpConfigTest() {
        // 模拟浏览器
        CloseableHttpClient httpClient= HttpClients.createDefault();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://www.sise.com.cn/index.html");

        // 配置请求信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) // 创建连接的最长时间，单位是毫秒
                .setConnectionRequestTimeout(500) // 设置获取连接的最长时间
                .setSocketTimeout(10*1000) // 设置数据传输的最长时间
                .build();
        // 给请求设置请求信息
        httpGet.setConfig(config);
        CloseableHttpResponse response = null;
        try {
            // 发送请求
            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("状态码："+statusCode);
            // 判断是否请求成功
            if(statusCode == 200){
                // 获取响应体
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity, "utf8");
                System.out.println("长度为："+content.length());
            }
            System.out.println("请求失败....");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 释放资源(断开连接)
                if (response != null)response.close();
                // 关闭浏览器
                if (httpClient != null)httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    void httpPostParamTest() throws Exception {
        // 模拟浏览器
        CloseableHttpClient httpClient= HttpClients.createDefault();

        // 创建Get请求
        HttpPost httpPost = new HttpPost("http://baidu.com");
        // 传递参数
        // 封装表单数据
        List<NameValuePair> param = new ArrayList<>();
        param.add(new BasicNameValuePair("keys","java"));

        // 创建表单的Entity对象
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(param, "utf8");
        httpPost.setEntity(formEntity);

        CloseableHttpResponse response = null;
        try {
            // 发送请求
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("状态码："+statusCode);
            // 判断是否请求成功
            if(statusCode == 200){
                // 获取响应体
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity, "utf8");
                System.out.println("长度为："+content.length());
            }
            System.out.println("请求失败....");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 释放资源(断开连接)
                if (response != null) response.close();
                // 关闭浏览器
                if (httpClient != null) httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void httpGetParamTest() throws Exception {
        // 模拟浏览器
        CloseableHttpClient httpClient= HttpClients.createDefault();
        // 传递参数 URI
        URIBuilder uriBuilder = new URIBuilder("http://baidu.com");
        uriBuilder.setParameter("key","java");
        // 创建Get请求
        HttpGet httpGet = new HttpGet(uriBuilder.build());

        CloseableHttpResponse response = null;
        try {
            // 发送请求
            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("状态码："+statusCode);
            // 判断是否请求成功
            if(statusCode == 200){
                // 获取响应体
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity, "utf8");
                System.out.println("长度为："+content.length());
            }
            System.out.println("请求失败....");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 释放资源(断开连接)
                if (response != null) response.close();
                // 关闭浏览器
                if (httpClient != null) httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    void httpGetTest() {
        // 模拟浏览器
        CloseableHttpClient httpClient= HttpClients.createDefault();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://www.sise.com.cn/index.html");

        CloseableHttpResponse response = null;
        try {
            // 发送请求
            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("状态码："+statusCode);
            // 判断是否请求成功
            if(statusCode == 200){
                // 获取响应体
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity, "utf8");
                System.out.println("长度为："+content.length());
            }
            System.out.println("请求失败....");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 释放资源(断开连接)
                if (response != null)response.close();
                // 关闭浏览器
                if (httpClient != null)httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*

    @Test
    void contextLoads() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://www.sise.com.cn/");
        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpGet);
        System.out.println(response.getEntity().getContent());
        System.out.println(EntityUtils.toString(response.getEntity()));

    }

    @Test
    void doGetTestOne() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://www.sise.com.cn/index.html");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

*/
}
