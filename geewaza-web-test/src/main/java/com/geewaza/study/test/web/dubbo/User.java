package com.geewaza.study.test.web.dubbo;

import java.io.Serializable;

/**
 * Created by wangh on 2016/8/21.
 */
public class User implements Serializable {

	private String name;
	private int age;
	private String sex;

	public User(String name, int age, String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "{" +
				"name=" + name +
				", age=" + age +
				", sex=" + sex +
				"}";
	}
}
