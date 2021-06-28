package com.linear.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 58.6、异常处理类
 *      用来处理 SysException 类型的异常
 */
public class SysExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o,
            Exception e
    ) {
        // 针对SysException类型的异常，去做出处理
        SysException ex = null;
        if(e instanceof SysException){
            ex = (SysException)e;
        }else{
            ex = new SysException("系统正在维护中...");
        }

        // 处理结果就是去返回一个error视图
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg", e.getMessage());
        mv.setViewName("error");

        return mv;
    }
}
