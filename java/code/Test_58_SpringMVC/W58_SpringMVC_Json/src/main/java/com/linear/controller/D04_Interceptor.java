package com.linear.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 58.7、拦截器  interceptor
 *      设置了拦截器之后，所有的请求都会被拦截
 *      请求到达拦截器，然后拦截器放行，最后才到达controller
 */
@Controller
@RequestMapping("/interceptor")
public class D04_Interceptor {

    @RequestMapping("/b")
    public String interceptor(){
        System.out.println("interceptor 接口执行了");
        return "success";
    }

    @RequestMapping("/exclude")
    public String interceptorExclude(){
        System.out.println("不受拦截器影响的接口执行了");
        return "success";
    }
}
