package com.geewaza.study.mybatis.main;

import com.geewaza.study.mybatis.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangh on 2016/9/14.
 */
public class MybatisMain {

	public static void main(String[] args) {
		test01();
	}

	private static void test01() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		UserDao userDao = (UserDao) context.getBean("userDao");
		System.out.println(userDao.findById(1));

	}
}
