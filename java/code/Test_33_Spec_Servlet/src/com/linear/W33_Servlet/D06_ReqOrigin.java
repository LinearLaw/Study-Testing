package com.linear.W33_Servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    33.6、request的转发 - 来源Servlet

 */
@WebServlet("/forigin")
public class D06_ReqOrigin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("---ready to forward");

        req.setAttribute("msg","---[forward msg]");

        // 将当前的请求转发到/ftarget servlet
        req.getRequestDispatcher("/ftarget").forward(req,resp);
    }
}
