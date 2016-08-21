package com.geewaza.study.test.web.dubbo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangh on 2016/8/21.
 */
public class HelloServiceImpl implements HelloService {
	@Override
	public String sayHello(String name) {
		return "Hello! " + name;
	}

	@Override
	public List<User> getUsers() {
		List<User> userList = new ArrayList<User>();
		User u1 = new User("Jack", 18, "男");
		User u2 = new User("Tom", 20, "男");
		User u3 = new User("Lucy", 18, "女");
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		return userList;
	}
}
