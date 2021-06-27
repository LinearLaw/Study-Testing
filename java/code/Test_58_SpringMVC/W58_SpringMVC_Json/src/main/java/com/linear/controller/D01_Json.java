package com.linear.controller;

import com.linear.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 58.4、响应json数据，
 *      使用jackson自动将类转换成json数据返回
 *      用@ResponseBody声明返回值
 */
@Controller
@RequestMapping("/json")
public class D01_Json {

    @RequestMapping("/user")
    public @ResponseBody User testjson(){
        User user = new User();
        user.setName("aaaa");
        user.setAge(123);

        return user;
    }
}
