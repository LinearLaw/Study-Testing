package com.linear.domain;

import java.util.Date;
import java.util.List;

public class User {
    private String name;
    private Integer age;
    private Date date;

    private List<Account> list;

    public List<Account> getList() {
        return list;
    }

    public void setList(List<Account> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{ name='" + name + '\'' + ", age=" + age +
                ", date=" + date + ", list=" + list + '}';
    }
}
