package com.geewaza.springmvc.controller;

import com.alibaba.fastjson.JSON;

/**
 * Created by wangh on 2016/8/28.
 */
public class LoginCommand {

	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return JSON.toJSON(this).toString();
	}
}
