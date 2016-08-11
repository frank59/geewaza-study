package com.geewaza.springmvc.test.service;

import com.geewaza.springmvc.test.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangh on 2016/8/11.
 */
public class UserService {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	public void createUser(User user) {

		logger.info("Create user: " + user);

	}

}
