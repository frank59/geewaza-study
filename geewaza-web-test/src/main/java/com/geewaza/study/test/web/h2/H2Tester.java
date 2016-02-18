package com.geewaza.study.test.web.h2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 * Created by WangHeng on 2016/2/18.
 */
public class H2Tester {
	private static final String SPRING_FILE = "classpath:spring-config.xml";
	public static void main(String[] args) {
		test04();
	}

	private static void test04() {

		ApplicationContext context = new FileSystemXmlApplicationContext(SPRING_FILE);

		H2TestDao dao = context.getBean("h2TestDao", H2TestDao.class);
		System.out.println(dao.select());
		System.exit(0);
	}

	private static void test03() {

		ApplicationContext context = new FileSystemXmlApplicationContext(SPRING_FILE);

		H2TestDao dao = context.getBean("h2TestDao", H2TestDao.class);
		dao.createTest();
		dao.insert();
		System.out.println(dao.select());
		System.exit(0);
	}

	private static void test02() {
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath() + "search.properties");

	}

	private static void test01() {

		ApplicationContext context = new FileSystemXmlApplicationContext(SPRING_FILE);

		H2TestDao dao = context.getBean("h2TestDao", H2TestDao.class);
		dao.createTest();


		System.exit(0);
	}

}
