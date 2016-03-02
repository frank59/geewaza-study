package com.geewaza.web.test.spider;

import com.geewaza.study.commons.spider.SpiderUtil;
import com.geewaza.study.test.web.spider.LOLChampionSpider;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangHeng on 2016/2/2.
 */

public class SpiderTester {
	private static final String SPRING_FILE = "classpath:spring-config.xml";

	public static void main(String[] args) throws Exception {
		test01();
	}

	private static void test02() throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext(SPRING_FILE);
		String jsFile = "http://lol.qq.com/biz/hero/XinZhao.js";
		SpiderUtil spiderUtil = context.getBean("spiderUtil", SpiderUtil.class);
		SpiderUtil.HttpRes httpRes = spiderUtil.getRes(jsFile, false);
		String responseBody = httpRes.getSource();
		System.out.println(responseBody);

	}

	private static void test01() throws Exception {
		String outputFileName = "/tmp/lolchampions.txt";
		ApplicationContext context = new FileSystemXmlApplicationContext(SPRING_FILE);
		LOLChampionSpider lolChampionSpider = context.getBean("lolChampionSpider", LOLChampionSpider.class);
		List<LOLChampionSpider.LOLChampion> lolChampions = lolChampionSpider.pullOfficialChampionData();

		List<String> championLines = new ArrayList<String>();
		for (LOLChampionSpider.LOLChampion lolChampion : lolChampions) {
			championLines.add(lolChampion.toString());
		}
		FileUtils.writeLines(new File(outputFileName), championLines);
		System.exit(0);
	}
}
