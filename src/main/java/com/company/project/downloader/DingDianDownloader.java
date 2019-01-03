package com.company.project.downloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

public class DingDianDownloader implements PageProcessor{

	private final String BOOK="https://www.x23us.com/book/25888";

	private final String CHAPTER="https://www.x23us.com/html/25/25888/21801535.html";
	
	public static void main(String[] args) {	
		HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
    	SimpleProxyProvider proxyProvider = SimpleProxyProvider.from(new Proxy("proxysz.aac.com", 80));
    	httpClientDownloader.setProxyProvider(proxyProvider);
    	Spider.create(new DingDianDownloader()).thread(5).setDownloader(httpClientDownloader).addUrl("https://www.x23us.com/book/25888").run();
	}

	@Override
	public void process(Page page) {
		String url=page.getHtml().$(".read").links().get();
		System.out.println(url);
	}

	@Override
	public Site getSite() {
		return Site.me().setRetryTimes(3).setRetrySleepTime(100);
	}

}
