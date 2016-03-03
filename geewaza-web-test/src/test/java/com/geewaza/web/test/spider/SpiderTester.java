package com.geewaza.web.test.spider;

import com.geewaza.study.commons.spider.SpiderUtil;
import com.geewaza.study.test.web.spider.LOLChampionSpider;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangHeng on 2016/2/2.
 */

public class SpiderTester {
	private static final String SPRING_FILE = "classpath:spring-config.xml";

	public static void main(String[] args) throws Exception {
		test04();
	}

	private static void test04() throws Exception {
		String filePrefix = "/tmp/skin/";
		ApplicationContext context = new FileSystemXmlApplicationContext(SPRING_FILE);
		LOLChampionSpider lolChampionSpider = context.getBean("lolChampionSpider", LOLChampionSpider.class);
		List<LOLChampionSpider.LOLChampion> lolChampions = lolChampionSpider.pullOfficialChampionData();
		for (LOLChampionSpider.LOLChampion lolChampion : lolChampions) {
			String championId = lolChampion.getId();
			String fileName = championId + lolChampion.getSkin().substring(lolChampion.getSkin().lastIndexOf("."), lolChampion.getSkin().length());
			File outPutFile = new File(filePrefix + fileName);
			URL fileUrl = new URL(lolChampion.getSkin());
			FileUtils.copyURLToFile(fileUrl, outPutFile);
		}
		System.exit(0);
	}

	private static void test03() throws Exception {
		String filePrefix = "/tmp/champion/";
		ApplicationContext context = new FileSystemXmlApplicationContext(SPRING_FILE);
		LOLChampionSpider lolChampionSpider = context.getBean("lolChampionSpider", LOLChampionSpider.class);
		List<LOLChampionSpider.LOLChampion> lolChampions = lolChampionSpider.pullOfficialChampionData();
		for (LOLChampionSpider.LOLChampion lolChampion : lolChampions) {
			String fileName = lolChampion.getImage().substring(lolChampion.getImage().lastIndexOf("/") + 1, lolChampion.getImage().length());
			File outPutFile = new File(filePrefix + fileName);
			URL fileUrl = new URL(lolChampion.getImage());
			FileUtils.copyURLToFile(fileUrl, outPutFile);
		}
		System.exit(0);
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
