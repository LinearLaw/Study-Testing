package com.linear.controller;

import com.linear.domain.Account;
import com.linear.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/param")
public class D02_Param {
    /**
     * 58.2.1、参数绑定 - 基本类型
     * @return
     */
    @RequestMapping("/basic")
    public String basicParam(String name,String password){
        System.out.println("基本类型:" + name + "   " + password);
        return "success";
    }

    /** 58.2.2、java bean类型
     *  只要传的数据，参数名和account类的成员同名，就会自动封装
     * */
    @RequestMapping("/bean")
    public String beanParam(Account account){
        System.out.println(account);
        return "success";
    }

    /**
     * 59.2.3、使用自定义的转换类
     *      三步：
     *          - 设置一个类，实现Converter接口
     *          - 设置xml中，ConversionService的bean对象，并指定自己定义的类
     *          - 设置xml中，mvc:annotation-driven的conversion-service指向这个bean
     */
    @RequestMapping("/convert")
    public String convertParam(User user) {
        System.out.println(user);
        return "success";
    }

    /**
     * 58.2.4、集合类型  list  map
     */
    @RequestMapping("/list")
    public String listParam(User user){
        System.out.println(user);
        return "success";
    }

}
