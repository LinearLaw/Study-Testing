package com.linear.D01_Injection.service.impl;

import com.linear.D01_Injection.service.IAccountService;

import java.util.Date;

/**
 * 57.1、业务层实现类 - set注入
 */
public class AccountServiceImpl2 implements IAccountService {

    private String name;
    private Integer age;
    private Date birthday;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }

    public void saveAccount(){
        System.out.println("账户保存了: "+name+", "+age+", "+birthday);
    }
}
