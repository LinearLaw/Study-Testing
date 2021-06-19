package com.linear.D01_Injection.ui;

import com.linear.D01_Injection.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 57.1 模拟的表现层
 */
public class Client {

    public static void main(String[] args){
        // 1、获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("D01_Injection.xml");

        System.out.println("\n1、构造函数注入");
        // 2、根据id获取bean对象
        IAccountService as = (IAccountService)ac.getBean("accountService");
        as.saveAccount();

        System.out.println("\n2、set注入");
        IAccountService as2 = (IAccountService)ac.getBean("accountServiceSet");
        as2.saveAccount();

        System.out.println("\n3 set注入 - 集合");
        IAccountService as3 = (IAccountService)ac.getBean("accountServiceSetCollection");
        as3.saveAccount();

    }
}
