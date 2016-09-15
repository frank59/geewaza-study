package com.geewaza.springmvc.dao;


import com.geewaza.springmvc.domain.User;

/**
 * Created by wangh on 2016/9/15.
 */
public interface UserDao {

	int getMatchCount(String userName, String password);
	User findUserByUserName(String userName);
	void updateLoginInfo(User user);
}
