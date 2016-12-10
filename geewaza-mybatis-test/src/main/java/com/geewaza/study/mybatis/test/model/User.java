package com.geewaza.study.mybatis.test.model;

import com.geewaza.study.mybatis.test.common.Gender;

/**
 * Created by wangh on 2016/12/10.
 */
public class User {
    private int id;
    private String name;
    private int age;
    private Gender gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public User(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public User() {
    }
}
