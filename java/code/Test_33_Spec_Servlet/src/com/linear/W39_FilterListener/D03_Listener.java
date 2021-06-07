package com.linear.W39_FilterListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/*
    39.3、Listener 监听器
    ServletContextListener - 主要用来监听ServletContext的创建和销毁

 */
@WebListener
public class D03_Listener implements ServletContextListener {

    /**
     * 初始化
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("-----------[ServletContext 被创建]");
    }

    /**
     * 监听器销毁
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("-----------[ServletContext 被销毁]");
    }
}
