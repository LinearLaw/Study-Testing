package com.linear.B07_APIs;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Scanner;

/*
    7.1、Scanner 文本扫描器
    可以解析基本类型 or 字符串的简单文本扫描


 */
public class D01_Scanner {

    public static void main(String[] args){

        System.out.println("--------------");
        //run01();

        System.out.println("--------------");
        //run02();

        System.out.println("--------------");
        run03();

    }
    // 1、basic
    public static void run01(){
        /* 初始化Scanner，这里需要传入一个输入流
        *   这里使用System.in作为输入流，即键盘输入
        * */
        Scanner sc = new Scanner(System.in);

        // 将输入的内容当做是一个int获取
        int num = sc.nextInt();
        System.out.println(num);

        // 默认是将输入的内容当做是string
        String str = sc.next();
        System.out.println(str);
    }

    // 2、get sum
    public static void run02(){
        Scanner sc = new Scanner(System.in);

        System.out.println("输入第一个数字：");
        int a = sc.nextInt();

        System.out.println("输入第二个数字：");
        int b = sc.nextInt();

        System.out.println("得到的和为："+(a+b));
    }

    // 3、get max
    public static void run03(){
        Scanner sc= new Scanner(System.in);

        System.out.println("Add 1st num");
        int a = sc.nextInt();

        System.out.println("Add 2nd num");
        int b = sc.nextInt();

        System.out.println("Add 3rd num");
        int c = sc.nextInt();

        int temp = a> b?a:b;
        int max = temp>c?temp:c;
        System.out.println("Max:"+ max);

    }

}
