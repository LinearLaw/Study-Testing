package com.linear.W39_FilterListener;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/*
    39.1、过滤器
    生命周期
    - init
    - doFilter
    - destroy

    @WebFilter，可以匹配多种路径
        /*          所有
        /index.jsp  特定路径
        /user/*     user下的所有

    dispatcherTypes
        - DispatcherType.REQUEST    默认使用，直接请求时触发该过滤器
        - DispatcherType.FORWARD    转发的时候，触发该过滤器

        可以接多个值
        dispatcherTypes = { DispatcherType.REQUEST, DipatcherType.FORWARD }

 */
@WebFilter("/*")
public class D01_LifeCycle implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("[filter][all] Filter init");
    }

    /**
     * 每一个符合WebFilter的请求URL，都会经过doFilter，然后才给到具体的Servlet
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException
    {
        System.out.println("[filter][all] Do Filter");

        // 放行，如果不放行，后面的servlet全都会失效。
        filterChain.doFilter(servletRequest,servletResponse);

        // 在其他Servlet处理完成后，还可以继续做一些操作
        System.out.println("[filter][all] After servlet run, run fiter continue");
    }

    @Override
    public void destroy() {
        System.out.println("[filter][all] Filter run stop");
    }
}
