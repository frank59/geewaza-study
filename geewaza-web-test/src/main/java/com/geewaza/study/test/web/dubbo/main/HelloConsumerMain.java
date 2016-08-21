package com.geewaza.study.test.web.dubbo.main;

import com.geewaza.study.test.web.dubbo.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangh on 2016/8/21.
 */
public class HelloConsumerMain {
	private static final String CONTEXT_FILE = "dubbo-consumer-spring-config.xml";

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:" + CONTEXT_FILE);

		HelloService helloService = context.getBean("helloService", HelloService.class);
		System.out.println(helloService.sayHello("Frank"));
		System.out.println(helloService.getUsers());
	}
}
