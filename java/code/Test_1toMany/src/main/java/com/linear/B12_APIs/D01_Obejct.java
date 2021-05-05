package com.linear.B12_APIs;


import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.Console;
import java.util.ArrayList;
import java.util.Objects;

/*
    12.1、Object
        Objects

 */
public class D01_Obejct {

    public static void main(String[] args){
        run01();
        System.out.println("---------");

        run02();
        System.out.println("---------");

        run03();
        System.out.println("---------");

    }
    // toString equals
    public static void run01(){
        // 打印
        Person p = new Person("张三",18);
        System.out.println(p.toString());

        // 判断对象相等
        Person p2 = p;
        System.out.println(p.equals(p2));
    }

    // toString
    public static void run02(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(23242);
        list.add(6546);
        list.add(999);
        list.add(65);
        list.add(989);

        String ls = list.toString();
        System.out.println(ls);

        int a = list.hashCode();
        System.out.println(a);
    }

    // Obejcts equals
    public static void run03(){
        // Objects提供了一系列操作对象的工具类
        Person p1 = new Person("Trump",100);
        Person p2 = new Person("Abama",200);

        boolean b = Objects.equals(p1,p2);
        System.out.println(b);
    }

}
