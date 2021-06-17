package com.linear.D01_Injection.service.impl;

import com.linear.D01_Injection.service.IAccountService;

import java.util.Date;

/**
 * 57.1、业务层实现类 - 构造函数注入
 */
public class AccountServiceImpl implements IAccountService {

    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl(String name, Integer age, Date birthday){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void saveAccount(){
        System.out.println("账户保存了: "+name+", "+age+", "+birthday);
    }
}
