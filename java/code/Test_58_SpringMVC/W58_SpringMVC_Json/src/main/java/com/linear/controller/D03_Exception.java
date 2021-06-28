package com.linear.controller;

import com.linear.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 58.6、假如有一个接口，获取了请求后，
 *      然后处理过程中，抛出了一个SysException异常
 */
@Controller
@RequestMapping("/ex")
public class D03_Exception {

    @RequestMapping("/sys")
    public String testException() throws SysException{
        System.out.println("test exception running");

        try {
            int a = 10/0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException("接口出错...");
        }

        return "success";
    }
}
