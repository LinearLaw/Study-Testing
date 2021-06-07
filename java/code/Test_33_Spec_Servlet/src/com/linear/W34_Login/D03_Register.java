package com.linear.W34_Login;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/*
    34.3、注册，
        先查询是否有这个用户存在，
            存在 - 失败
            不存在 - 成功

 */
@WebServlet("/doRegister")
public class D03_Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf8");

        // 1、获取请求参数
        Map<String,String[]> parameterMap = req.getParameterMap();

        // 2、验证char code是否正确 - 统一转成小写
        String charcode = req.getParameter("charcode").toLowerCase();

        String scode = ((String)req.getSession().getAttribute("CS"))
                .toLowerCase();
        if( !charcode.equals(scode) ){
            resp.getWriter().print("{\"msg\":\"验证码错误\",\"code\":-1}");
            System.out.println("[register failed] charcode error");
            return;
        }

        try {
            // 3、查询用户是否存在
            String find = "select count(username) from user where username = ?";
            QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());

            // 使用BeanUtils来完成User成员的映射
            User user = new User();
            BeanUtils.populate(user,parameterMap);

            Long count = (Long) qr.query(find,
                    new ScalarHandler(),
                    user.getUsername()
            );

            if(count > 0){
                resp.getWriter().print("user existed");
            }else{
                // 将新用户插入到表中
                String ins = "insert into user(username,password) values(?,?)";
                int rows = qr.update(ins, user.getUsername(),user.getPassword());
                if(rows > 0){
                    resp.getWriter().print("{\"msg\":\"注册成功\",\"code\":1}");
                    System.out.println("[register success]");
                }else{
                    resp.getWriter().print("{\"msg\":\"注册失败\",\"code\":-1}");
                    System.out.println("[register failed]");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
