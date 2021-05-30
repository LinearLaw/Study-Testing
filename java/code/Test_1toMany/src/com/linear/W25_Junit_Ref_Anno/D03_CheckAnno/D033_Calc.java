package com.linear.W25_Junit_Ref_Anno.D03_CheckAnno;

/*
    25.3、自定义注解 - 自定义类 - 计算类
        拥有多个方法，并加上了自定义注解
 */
public class D033_Calc {

    @D032_Check
    public void add(){
        System.out.println("1+1=2");
    }

    @D032_Check
    public void minus(){
        System.out.println("1-1=0");
    }

    @D032_Check
    public void div(){
        System.out.println("5/5=1");

    }
}
