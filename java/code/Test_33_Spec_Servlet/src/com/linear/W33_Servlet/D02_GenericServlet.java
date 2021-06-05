package com.linear.W33_Servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

/*
    33.2、GenericServlet
        对Servlet接口的方法，进行了默认实现
        继承GnericServlet后，只需要重写service方法即可。

 */
@WebServlet("/demo2")
public class D02_GenericServlet extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException
    {
        System.out.println("GenericServlet 收到了请求");
    }
}
