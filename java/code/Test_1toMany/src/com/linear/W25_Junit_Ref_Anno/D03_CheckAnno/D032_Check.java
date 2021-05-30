package com.linear.W25_Junit_Ref_Anno.D03_CheckAnno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    25.2、声明一个注解
        @Retention(RetentionPolicy.RUNTIME)     该注解作用在运行时阶段
        @Target(ElementType.METHOD)     该注解作用到某一个方法上

 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface D032_Check {


    // 这里写注解成员

}
