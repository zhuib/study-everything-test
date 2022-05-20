package top.iaminlearn.crawler.task;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * Date: 2021/5/5 17:03
 */

@Component
public class ProxyTest  implements PageProcessor {

//    @Scheduled(fixedDelay = 1000)
    public void process() {
        // 创建下载器
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        // 给下载器设置代理服务器信息
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("58.255.207.126",9999)));
        Spider.create(new ProxyTest())
                .addUrl("https://ip.900cha.com/")
                .setDownloader(httpClientDownloader)
                .run();

    }
    @Override
    public void process(Page page) {
        System.out.println(Jsoup.parse(page.getHtml().css("ul.list-unstyled").toString()).text());
//        System.out.println(page.getHtml().toString());
    }

    private Site site = Site.me()
            .setCharset("utf8")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36 Edg/90.0.818.51");
    @Override
    public Site getSite() {
        return site;
    }
}
