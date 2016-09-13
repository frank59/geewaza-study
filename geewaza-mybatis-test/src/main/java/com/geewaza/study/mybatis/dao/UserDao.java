package com.geewaza.study.mybatis.dao;

import com.geewaza.study.mybatis.domain.User;

/**
 * Created by wangh on 2016/9/14.
 */
public interface UserDao {

	User findById(int id);

	void insert(User user);
}
