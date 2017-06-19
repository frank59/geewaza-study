package com.geewaza.study.dao.test;

import java.util.List;

import com.geewaza.study.dao.UserDAO;
import com.geewaza.study.dao.pojo.UserDO;
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
        UserDO user = new UserDO();
        user.setId(1);
        user.setName("Tom");

        UserDAO userDAO = context.getBean("userDAO", UserDAO.class);
        int result = userDAO.insert(user);
        System.out.println(result);

    }
    @Test
    public void queryUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        UserDAO userDAO = context.getBean("userDAO", UserDAO.class);
        List<UserDO> userList = userDAO.selectAll();
        System.out.println(userList);

    }
}
