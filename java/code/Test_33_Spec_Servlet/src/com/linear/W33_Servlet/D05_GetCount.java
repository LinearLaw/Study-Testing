package com.linear.W33_Servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    33.5、结合ServletContext，获取网站访问次数

 */
@WebServlet("/show")
public class D05_GetCount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        resp.setContentType("text/html;utf-8");

        ServletContext servletCtx = getServletContext();

        // 获取visitCount，返回到前端
        Integer visitCount = (Integer) servletCtx.getAttribute("visitCount");
        int i;
        if(visitCount == null){
            i = 1;
        }else{
            i = visitCount;
        }
        resp.getWriter().write("visit count : " + i);
    }
}
