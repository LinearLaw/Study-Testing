package com.linear.W39_FilterListener;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

/*
    39.2、使用代理模式，在Filter中，对request进行增强

 */
@WebFilter("/getCharCode")
public class D02_Proxy2Req implements Filter {

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain filterChain)
            throws IOException, ServletException
    {
        /*  针对req，做一个代理，在代理中对req可能执行的操作进行拦截
            方法拦截后，对方法的结果进行过滤。
        */
        ServletRequest proxyReq = (ServletRequest) Proxy.newProxyInstance(
                req.getClass().getClassLoader(),    // 获取class loader
                req.getClass().getInterfaces(),     // 获取相对应的interface
                new InvocationHandler() {   // 代理，拦截方法的调用，对方法进行额外处理
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable
                    {
                        System.out.println("[Strength Proxy]");
                        // 比如，去过滤掉一些敏感字、控制用户的访问权限

                        // 首先去获取方法的结果，然后对这个结果进行处理
                        Object value = (Object)method.invoke(req,args);

                        if(value != null){
                            Random ran = new Random();
                            System.out.println(
                                    "["+(ran.nextInt(8000)+1000)+"]" +
                                    "[filter proxy] Mehtod : " + method.getName());
                        }
                        if(method.getName().equals("getSession")){
                            return value;
                        }
                        return value;
                    }
                }
        );

        // 代理结束后，将增强后的req，发送给下一个Servlet
        filterChain.doFilter(proxyReq,res);
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("[filter][login] int");
    }

    @Override
    public void destroy() {
        System.out.println("[filter][login] destroy");
    }
}
