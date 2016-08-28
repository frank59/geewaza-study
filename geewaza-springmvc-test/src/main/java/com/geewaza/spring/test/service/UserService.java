package com.geewaza.spring.test.service;

import com.geewaza.spring.test.dao.LoginLogDao;
import com.geewaza.spring.test.dao.UserDao;
import com.geewaza.spring.test.domain.LoginLog;
import com.geewaza.spring.test.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangh on 2016/8/28.
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginLogDao loginLogDao;

	public boolean hasMatchUser(String userName, String password) {
		int matchCount = userDao.getMatchCount(userName, password);
		return matchCount > 0;
	}

	public User findUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}

	public void loginSuccess(User user) {
		user.setCredits(user.getCredits() + 5);
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getUserId());
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDate(user.getLastVisit());
		userDao.updateLoginInfo(user);
		loginLogDao.insertLoginLog(loginLog);
	}
}
