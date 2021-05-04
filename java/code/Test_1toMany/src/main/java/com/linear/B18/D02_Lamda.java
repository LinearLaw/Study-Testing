package com.linear.B18;

/*
    18.2、Lambda表达式  -  要求language level 8
        函数式编程
        (param)->{ function body }

    使用Lambda表达式的前提
        - 必须要有接口，且接口中有且仅有一个抽象方法
          - 无论是JDK内置的Runnable、Comparator接口还是自定义接口；
          - 只有当接口中抽象方法存在且唯一时，才可以用Lambda
        - 使用Lambda必须要有上下文推断
          - 方法参数或局部变量类型，需要为Lambda提供接口类型，这样编译器才会根据上下文推断出接口实例



Tips：有且仅有一个抽象方法的接口，称为函数式接口


 */
public class D02_Lamda {

    public static void main(String [] args){
        run01();

        run02();
    }

    // 1、测试
    public static void run01(){
        // 原本的写法
        new Thread(new Runnable() {
            public void run() {
                System.out.println("Runnable runing");
            }
        }).start();

        // 使用lambda后的写法
        new Thread(()->{
            System.out.println("Lambda runing");
        }).start();
    }

    // 2、自动实现接口
    // 执行函数
    public static void run02Impl(int a, int b, Calc cc){
        int res = cc.ca(a,b);
        System.out.println(res);
    }
    public static void run02(){
        // 第一种写法
        run02Impl(10,20,(int a, int b)->{return a+b;});
        // 更简洁的写法
        run02Impl(11,21,(int a, int b)->a+b);
    }
}
