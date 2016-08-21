package com.geewaza.study.test.web.dubbo.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangh on 2016/8/21.
 */
public class HelloProviderMain {

	private static final String CONTEXT_FILE = "dubbo-provider-spring-config.xml";

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:" + CONTEXT_FILE);

		while (true) {
			//保证程序不退出
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
