package com.linear.D02_AOPAnno.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/***
 * 57.6、 假设我们要打印日志。
 *    - 注解方式
 */
@Component("logger")
@Aspect         /** 这里注意，一定要加Aspect，来声明这是一个切面类 */
public class Logger {

    /** 配置 前置、后置、异常、最终通知 的 切面 */
    @Pointcut("execution(* com.linear.D02_AOPAnno.service.impl.AccountServiceImpl.*(..))")
    private void pt1(){}

    /** 1、前置通知 */
    @Before("pt1()")
    public void beforeLog(){
        System.out.println("before log : 前置通知");
    }

    /** 2、后置通知 */
    @AfterReturning("pt1()")
    public void afterReturningLog(){
        System.out.println("after returning log : 后置通知");
    }

    /** 3、异常通知 */
    @AfterThrowing("pt1()")
    public void afterThrowingLog(){
        System.out.println("after throwing log : 异常通知");
    }

    /** 4、最终通知 */
    @After("pt1()")
    public void finallyLog(){
        System.out.println("after log : 最终通知");
    }

    /** 5、环绕通知
     *      可以自己指定，在什么地方去执行方法，在什么地方增强。
     *      也就是提供了方法，可以自己定义方法的执行顺序。
     *
     *      默认会传入一个 ProceedingJoinPoint 类，
     *      ProceedingJoinPoint是一个接口，
     *      在程序执行时，spring会提供一个实现该接口的类
     *      使用这个类，proceed方法，就相当于是在执行原方法；
     * */
    // 配置环绕通知的切面
    @Pointcut("execution(* com.linear.D02_AOPAnno.service.impl.AccountServiceAround.*(..))")
    private void pt2(){}

    // 环绕通知
    @Around("pt2()")
    public Object aroundLog(ProceedingJoinPoint pjp){
        Object rtValue = null;

        Object[] args = pjp.getArgs();
        try {
            System.out.println("注解的环绕通知");

            beforeLog();
            rtValue = pjp.proceed(args); // 执行原方法
            afterReturningLog();
            return rtValue;
        } catch (Throwable throwable) {
            afterThrowingLog();
            throw new RuntimeException();
        } finally {
            finallyLog();
        }
    }
}
