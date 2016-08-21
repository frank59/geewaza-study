package com.geewaza.study.test.web.dubbo.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangh on 2016/8/21.
 */
public class HelloProviderMain {

	private static final String CONTEXT_FILE = "dubbo-provider-spring-config.xml";

	public static void main(String[] args) {

		ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext("classpath:" + CONTEXT_FILE);
		context.start();
		while (true) {
			//保证程序不退出
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("sleep 1sec..");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
