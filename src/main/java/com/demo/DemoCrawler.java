package com.demo;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Html;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * 运用gecco做java爬虫
 * @author liyut
 *
 */
@Gecco(matchUrl="https://github.com/{user}/{project}", pipelines="consolePipeline")
public class DemoCrawler implements HtmlBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4577087400486201557L;
	
	@RequestParameter("user")
	private String user;
	
	@RequestParameter("project")
    private String project;
	
	@Text
    @HtmlField(cssPath=".repository-meta-content")
    private String title;
	
	@Text
    @HtmlField(cssPath=".pagehead-actions li:nth-child(2) .social-count")
    private int star;

    @Text
    @HtmlField(cssPath=".pagehead-actions li:nth-child(3) .social-count")
    private int fork;
	
    @Html
    @HtmlField(cssPath=".entry-content")
    private String readme;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getFork() {
		return fork;
	}

	public void setFork(int fork) {
		this.fork = fork;
	}

	public String getReadme() {
		return readme;
	}

	public void setReadme(String readme) {
		this.readme = readme;
	}
    
	/**
	 * 测试
	 * 代码说明
	接口HtmlBean说明该爬虫是一个解析html页面的爬虫（gecco还支持json格式的解析）
	注解@Gecco告知该爬虫匹配的url格式(matchUrl)和内容抽取后的bean处理类（pipelines处理类采用管道过滤器模式，可以定义多个处理类）。
	注解@RequestParameter可以注入url中的请求参数，如@RequestParameter("user")表示匹配url中的{user}
	注解@HtmlField表示抽取html中的元素，cssPath采用类似jquery的css selector选取元素
	注解@Text表示获取@HtmlField抽取出来的元素的text内容
	注解@Html表示获取@HtmlField抽取出来的元素的html内容（如果不指定默认为@Html）
	GeccoEngine表示爬虫引擎，通过create()初始化，通过start()/run()运行。可以配置一些启动参数如：扫描@Gecco注解的包名classpath；开始抓取的url地址star；抓取线程数thread；抓取完一个页面后的间隔时间interval(ms)等
	 */
    public static void main(String[] args) {
		
		GeccoEngine.create()
		//gecco搜索包路径
		.classpath("com.demo")
		//开始抓取页面地址
		.start("https://github.com/xtuhcy/gecco")
		//开启几个爬虫线程
		.thread(1)
		//单个爬虫每次抓取完一个请求后的间隔时间
		.interval(2000)
		//启动线程
		.start();
		
	}
    
}
