package com.linear.D01_AOPXML.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/***
 * 57.5、 假设我们要打印日志。
 */
public class Logger {

    /** 1、前置通知 */
    public void beforeLog(){
        System.out.println("before log : 前置通知");
    }

    /** 2、后置通知 */
    public void afterReturningLog(){
        System.out.println("after returning log : 后置通知");
    }

    /** 3、异常通知 */
    public void afterThrowingLog(){
        System.out.println("after throwing log : 异常通知");
    }

    /** 4、最终通知 */
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
    public Object aroundLog(ProceedingJoinPoint pjp){
        Object rtValue = null;

        Object[] args = pjp.getArgs();
        try {
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
