package com.geewaza.study.mybatis.test;

import com.geewaza.study.mybatis.test.common.Gender;
import com.geewaza.study.mybatis.test.dao.UserDAO;
import com.geewaza.study.mybatis.test.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangh on 2016/12/11.
 */
@Component("userService")
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    /**
     * 初始化测试数据
     */
    public void initData() {
        logger.info("删除表");
        userDAO.dropTable();
        logger.info("重新建表");
        userDAO.createTable();
        logger.info("初始化数据");
        List<User> userList = initUserList();
        userDAO.saveUserList(userList);
        logger.info("初始化完毕.");
    }

    /***
     * 创建最初的几个测试数据
     * @return
     */
    private List<User> initUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("张三", 26, Gender.male));
        userList.add(new User("李四", 23, Gender.female));
        userList.add(new User("王五", 27, Gender.male));
        userList.add(new User("赵六", 25, Gender.female));
        userList.add(new User("猫七", 30, Gender.male));

        return userList;
    }

}
