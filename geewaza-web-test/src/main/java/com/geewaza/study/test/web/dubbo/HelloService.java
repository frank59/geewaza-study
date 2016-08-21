package com.geewaza.study.test.web.dubbo;

import java.util.List;

/**
 * Created by wangh on 2016/8/21.
 */
public interface HelloService {

	String sayHello(String name);

	public List<User> getUsers();
}
