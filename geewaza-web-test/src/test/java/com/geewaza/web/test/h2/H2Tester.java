package com.geewaza.web.test.h2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by WangHeng on 2016/2/18.
 */
public class H2Tester {
	private static final String SPRING_FILE = "classpath:spring-config.xml";
	public static void main(String[] args) {
		test01();
	}

	private static void test01() {

		ApplicationContext context = new FileSystemXmlApplicationContext(SPRING_FILE);

		H2TestDao dao = context.getBean("h2TestDao", H2TestDao.class);

		dao.createTest();
		System.exit(0);
	}

}
