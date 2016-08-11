package com.geewaza.springmvc.test.domain;

/**
 * Created by wangh on 2016/8/11.
 */
public class User {

	private String userName;
	private String password;
	private String realName;

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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Override
	public String toString() {
		return "{" +
				"userName=" + userName +
				",password=" + password +
				",realName=" + realName +
				"}";
	}
}
