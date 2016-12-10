package com.geewaza.study.mybatis.main;

import com.alibaba.fastjson.JSON;
import com.geewaza.study.mybatis.dao.UserDao;
import com.geewaza.study.mybatis.test.UserService;
import com.geewaza.study.mybatis.test.common.Gender;
import com.geewaza.study.mybatis.test.dao.UserDAO;
import com.geewaza.study.mybatis.test.model.User;
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
		UserService userService = context.getBean("userService", UserService.class);
		userService.initData();
		UserDAO userDAO = context.getBean("userDAO", UserDAO.class);
		System.out.println(JSON.toJSONString(userDAO.listAllUser()));
	}

	private static void test02() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		UserDAO userDAO = context.getBean("userDAO", UserDAO.class);
		System.out.println(JSON.toJSONString(userDAO.listAllUser()));
	}


}
