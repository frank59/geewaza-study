package com.geewaza.study.mybatis.test.dao;

import com.geewaza.study.mybatis.test.model.User;

import java.util.List;

/**
 * Created by wangh on 2016/12/10.
 */
public interface UserDAO {

    void dropTable();

    void createTable();

    void saveUser(User user);

    List<User> listAllUser();

    void saveUserList(List<User> userList);

    //    public void getUser(int id);
//    public void listUser(List<Integer> id);
}
