package com.demo;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * 医院信息爬虫
 * @author liyut
 *
 */
@Gecco(matchUrl="http://jiankang.baidu.com/juhe/index?qid=0&pssid=0&pvid=1460605378420789150&tn=NONE&zt=self&wd=&aType=13&key=%E5%84%BF%E7%AB%A5%E5%8C%BB%E9%99%A2", pipelines={"consolePipeline"})
public class HospitalCrawler implements HtmlBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5462905517586821532L;
	
	@Text
	@HtmlField(cssPath="div.row.item.card-private.first > div.col.title > div.health-hospital-title > a")
	private String hospitalInfo;
	
	
	
	public String getHospitalInfo() {
		return hospitalInfo;
	}



	public void setHospitalInfo(String hospitalInfo) {
		this.hospitalInfo = hospitalInfo;
	}



	public static void main(String[] args) {
		
		String url = "http://jiankang.baidu.com/juhe/index?qid=0&pssid=0&pvid=1460605378420789150&tn=NONE&zt=self&wd=&aType=13&key=%E5%84%BF%E7%AB%A5%E5%8C%BB%E9%99%A2";
		
		GeccoEngine.create()
		//gecco搜索包路径
		.classpath("com.demo")
		//开始抓取页面地址
		.start(url)
		//开启几个爬虫线程
		.thread(1)
		//单个爬虫每次抓取完一个请求后的间隔时间
		.interval(2000)
		//启动线程
		.start();
		
	}
}
