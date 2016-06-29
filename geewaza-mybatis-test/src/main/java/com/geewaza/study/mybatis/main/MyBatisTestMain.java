package com.geewaza.study.mybatis.main;

import com.geewaza.study.mybatis.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangh on 2016/6/28.
 */
public class MyBatisTestMain {

	public static void main(String[] args) {
		test02();
	}

	private static void test02() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");


	}

	private static void test01() {

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(MyBatisTestMain.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
		SqlSession session = sqlSessionFactory.openSession();
		try {

			User user = session.selectOne("com.geewaza.study.mybatis.model.UserMapper.getUser", 1);
			System.out.println(user);
		} finally {
			session.close();
		}
	}
}
