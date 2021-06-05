package com.linear.W33_Servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


/*
    33.1、Servlet
    Tomcat是一个Web服务器，也是一个Servlet的容器
        对于一个客户端的请求，由Tomcat接收，Tomcat给到Servlet，
        Servlet处理之后，将响应结果给Tomcat，再由Tomcat返回给客户端

 */
@WebServlet(value="/demo1",loadOnStartup = 1)
public class D01_Servlet implements Servlet {


    public D01_Servlet() {
        super();
    }


    /*  1、Servlet初始化时，执行init方法
    *       只会执行一次，在第一次收到请求时，当前Servlet类被加载，触发init函数
    *       然后之后的所有请求，都不会执行init方法了，直接转到service
    *
    *       也可以设置成一开始就加载，web.xml中设置load-on-startup，
    *           或者注解中设置loadOnStartUp
    * */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Servlet初始化");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /*
        2、每一次当前URL收到请求，则会调用该service
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("接受到了请求");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    /*
        3、服务器正常关闭的时候，会执行destroy，
        主要是关闭一些资源
     */
    @Override
    public void destroy() {
        System.out.println("Servlet停止");
    }


    // ------------------------------------------------------
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
