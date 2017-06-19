package com.geewaza.study.dao.test;

import java.util.List;

import com.geewaza.study.dao.UserMapper;
import com.geewaza.study.dao.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wangheng on 2017/6/20.
 */

public class UserDaoTester {


    @Test
    public void saveUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        User user = new User();
        user.setId(1);
        user.setName("Tom");

        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        int result = userMapper.insert(user);
        System.out.println(result);

    }
    @Test
    public void queryUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        List<User> userList = userMapper.selectAll();
        System.out.println(userList);

    }
}
