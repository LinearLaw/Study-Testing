package com.linear.W25_Junit_Ref_Anno.D03_CheckAnno;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
    25.3、自定义注解
        自动化测试注解，
        首先，我们定义一个自定义的类，该类有多个方法
            我们希望，给该类的方法上，要检查的类，加上自定义注解@Check，
            然后在Test测试类中，把所有带有Check注解的类测试一遍。

        关键：
            boolean method.isAnnotionPresent(Anno.Class)
                method的一个方法，传入一个注解的Class，返回该method是否使用了该注解。

        Tips：如果要用这个， 需要在Retention中注明RetentionPolicy.RUNTIME

 */
public class D031_TestCheck {

    public static void main(String[] args){
        // 1、获取 calc 对象
        D033_Calc calc = new D033_Calc();

        // 2、获取 calc 的字节码文件对象
        Class c = calc.getClass();

        // 3、获取 calc 的所有成员方法
        Method[] methods = c.getMethods();

        /* 4、遍历calc的所有成员方法，先判断是否存在D032_Check这个注解，
            如果有，则 method.invoke(calcObj);
                invoke的同时进行捕获异常，打印方法的异常信息，作为测试报告。
            如果没有则不操作。
        */
        int num = 0; // 统计异常方法
        for(Method met : methods){
            // 判断是否存在某一注解，存在则执行。
            if(met.isAnnotationPresent(D032_Check.class)){
                try {
                    met.invoke(c);
                } catch (Exception e) {
                    e.printStackTrace();
                    num++;
                }
            }
        }
    }
}
