package com.demo.webmagic;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/***
 * 依赖webmagic包爬虫
 * 
 * @author liyut
 * 
 */
public class DemoWebmagicCrawler implements PageProcessor {	
	
	private Site site = Site.me().setDomain("my.oschina.net");

	public void process(Page page) {
		List<String> links = page.getHtml().links()
				.regex("http://my\\.oschina\\.net/flashsword/blog/\\d+").all();
		page.addTargetRequests(links);
		page.putField(
				"title",
				page.getHtml()
						.xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1")
						.toString());
		page.putField("content", page.getHtml().$("div.content").toString());
		page.putField("tags",
				page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());
	}

	public Site getSite() {
		return site;

	}

	public static void main(String[] args) {
		Spider.create(new DemoWebmagicCrawler())
				.addUrl("http://my.oschina.net/flashsword/blog")
				.addPipeline(new ConsolePipeline()).run();
	}

}
