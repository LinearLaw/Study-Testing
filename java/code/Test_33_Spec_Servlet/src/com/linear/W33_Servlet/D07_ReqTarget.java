package com.linear.W33_Servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    33.6、request的转发 - 目标Servlet

 */
@WebServlet("/ftarget")
public class D07_ReqTarget extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        String forwardMsg = (String) req.getAttribute("msg");
        if(forwardMsg != null){
            System.out.println("From forward : " + forwardMsg);
        }else{
            System.out.println("Direct request");
        }

    }
}
