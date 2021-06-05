package com.linear.W33_Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
    33.3、HttpServlet，
        针对HTTP协议封装Servlet，简化相关操作
 */
@WebServlet("/demo3")
public class D03_HttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        System.out.println("-------get a new request -------");
        /* 1、获取请求信息
         *
         */
        // 1.1、请求方式
        String method = req.getMethod();
        System.out.println("method : " + method);
        // 1.2、虚拟目录
        String contextPath = req.getContextPath();
        System.out.println("context path : " + contextPath);

        // 1.3、Servlet目录
        String servletPath = req.getServletPath();
        System.out.println("servlet path : " + servletPath);

        // 1.34、get请求时，获取请求参数 -> get请求的参数会放到url的末尾
        String queryString = req.getQueryString();
        System.out.println("queryString : " + queryString);

        System.out.println("--------------");

        /* 2、获取请求头信息 - 先获取header name的迭代器
         *
         */
        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();

            // 传入header字段名，获取字段值
            String value = req.getHeader(name);
            System.out.println(name + " : " + value);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        super.doPost(req, resp);
    }
}
