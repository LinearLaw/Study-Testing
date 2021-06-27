package com.linear.controller;

import com.linear.domain.Account;
import com.linear.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/anno")
public class D03_AnnoParam {
    /**
     * 58.3、常用注解
     *      - @RequestParam
     *      - @RequestBody
     *      - @PathVariable
     *      - @RequestHeader
     *      - @CookieValue
     *      - @ModelAttribute - 复杂点
     *      - @SessionAttribute - 复杂点
     */

    /** 58.3.1、RequestParam，用来映射 请求参数 -> 处理器形参    √ */
    @RequestMapping("/reqparam")
    public String reqParam(@RequestParam("username") String name){
        System.out.println(name);
        return "success";
    }

    /** 58.3.2、RequestBody 用来获取整个请求体的数据    √ */
    @RequestMapping("/reqbody")
    public String reqBody(
            @RequestBody String body,
            @RequestParam("username") String name
    ){
        System.out.println("body : " + body);
        System.out.println("name : " + name);
        return "success";
    }

    /** 58.3.3、PathVariable 用来做动态路由参数
     *      注意需要指明 路由name 的具体名称
     * */
    @RequestMapping("/path/{id}")
    public String pathVar(
            @PathVariable(name="id") String sid ,
            @RequestBody String body
    ){
        System.out.println("id:" + sid);
        System.out.println("body : " + body);
        return "success";
    }

    /** 58.3.4、RequestHeader，用来指定 header头
     *      注意需要指定具体header的字段名   √
     * */
    @RequestMapping("/reqheader")
    public String reqHeader(
            @RequestHeader("token") String a
    ){
        System.out.println("header : " + a);
        return "success";
    }

    /** 58.3.5、CookieValue，用来获取Cookie  √
     *      注意需要指明cookie的具体字段名
     * */
    @RequestMapping("/reqcookie")
    public String cookieVal(@CookieValue("JSESSIONID") String ccc){
        System.out.println(ccc);
        return "success";
    }

    /** 58.3.6、
     *      - @ModelAttribute - 复杂点
     *          传入一个User，只传了name，其他都没传，
     *          那其他的信息就要从数据库去读，用数据库的值，然后name值单独进行更新。
     *          此时就要用ModelAttribute
     *
     *      - @SessionAttribute - 复杂点 - 不用了
     */
    @RequestMapping("/model")
    public String modelAttr(User user){
        System.out.println("model attribute");
        return "success";
    }

    /** 58.3.7、ServletAPI */
    @RequestMapping("/servletapi")
    public String servletApi(HttpServletRequest req, HttpServletResponse res){
        Cookie[] coos = req.getCookies();
        for (Cookie i : coos){
            System.out.println(i.getName());
        }
        System.out.println(req.getPathInfo());
        System.out.println(req.getMethod());

        // 可以获取到session
        Long stamp = new Date().getTime();
        String aaa = (String)req.getSession().getAttribute("AAA");
        System.out.println(aaa);

        req.getSession().setAttribute("AAA",stamp);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/req")
    public Account testJson(){
        Account user = new Account();
        user.setMoney(123d);

        System.out.println(user);
        return user;
    }


}
