package com.linear.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/hello")
public class D01_HelloWorld {

    /***
     * 58.1、hello world
     * @return
     */
    @RequestMapping("/world")
    public String hello(){
        System.out.println("hello world controller");
        return "success";
    }

    /***
     * 58.2、RequestMapping
     */
    @RequestMapping(
            value="/mapping",
            params={"username=hei"},
            headers={"Accept"}
    )
    public String testRequestMapping(){
        System.out.println("RequestMapping run");
        return "success";
    }

}
