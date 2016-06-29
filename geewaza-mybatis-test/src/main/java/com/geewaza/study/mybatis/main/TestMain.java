package com.geewaza.study.mybatis.main;

import com.geewaza.study.mybatis.generator.mapper.UserMapper;
import com.geewaza.study.mybatis.generator.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by WangHeng on 2016/6/29.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class TestMain {

    @Resource
    private UserMapper  userMapper;
    @Test
    public void test() {
        User user = new User();
        user.setAge(10);
        user.setName("wangheng");
        userMapper.insert(user);
    }

}
