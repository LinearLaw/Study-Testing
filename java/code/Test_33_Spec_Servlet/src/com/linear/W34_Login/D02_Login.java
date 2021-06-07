package com.linear.W34_Login;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    34.1、登陆接口

 */
@WebServlet("/doLogin")
public class D02_Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf8");

        String username = (String)req.getParameter("username");
        String password = (String)req.getParameter("password");

        // 2、验证char code是否正确 - 统一转成小写
        String charcode = req.getParameter("charcode").toLowerCase();

        String scode = ((String)req.getSession()
                .getAttribute("CS"))
                .toLowerCase();
        if( !charcode.equals(scode) ){
            resp.getWriter().print("{\"msg\":\"验证码错误\",\"code\":-1}");

            System.out.println("[login failed] charcode error");
            return;
        }

        // 查数据库表，判断当前用户信息是否正确
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        try {
            // 通过username和password，确定表中是否有符合的用户
            String sql = "select * from user where username=? and password=?";

            User user = null;
            user = qr.query(sql,
                    new BeanHandler<User>(User.class),
                    username,
                    password
            );

            if(user != null){
                // 登陆成功
                resp.getWriter().print("{\"msg\":\"登录成功\",\"code\":1}");
                System.out.println("[login success]");
            }else{
                // 登陆失败
                resp.getWriter().print("{\"msg\":\"登录失败\",\"code\":-1}");
                System.out.println("[login failed]");
            }
        } catch (Exception e) {

            e.printStackTrace();

            resp.getWriter().print("server runtime error");
        }
    }
}
