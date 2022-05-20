package top.iaminlearn.crawler;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;

/**
 * Date: 2021/5/4 16:40
 */
public class HttpClientPool {

    public static void main(String[] args) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 设置最大的连接数
        cm.setMaxTotal(100);
        // 设置每个主机的最大连接数（均匀的分配每个主机的连接数）
        cm.setDefaultMaxPerRoute(10);

        // 使用连接池管理器发起请求
        doGet(cm);
        doGet(cm);
    }

    private static void doGet(PoolingHttpClientConnectionManager cm) {
        // 不是每次创建新的HttpClient，而是从连接池中获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        HttpGet httpGet = new HttpGet();

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (response !=null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 不能关闭httpClient ,由连接池管理httpclient
//                httpClient.close();
            }
        }
    }

}
