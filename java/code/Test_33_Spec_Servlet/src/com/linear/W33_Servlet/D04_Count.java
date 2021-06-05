package com.linear.W33_Servlet;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    33.4、使用ServletContext，统计访问网站的次数
        - 收到请求
        - 获取ServletContext对象
        - setAttribute设置visitCount信息
        - getAttribute获取visitCount信息
 */
@WebServlet("/count")
public class D04_Count extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {
        this.doPost(req,res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException,IOException
    {
        // 先获取visitCount，如果没有，设置为1；如果有，进行++；
        ServletContext servletCtx = getServletContext();
        Integer visitCount = (Integer) servletCtx.getAttribute("visitCount");
        if(visitCount != null){
            servletCtx.setAttribute("visitCount",visitCount+1);
        }else{
            servletCtx.setAttribute("visitCount", 1);
        }

        res.setContentType("text/html;utf-8");
        res.getWriter().write("<h1>Welcome !</h1>");
    }
}
