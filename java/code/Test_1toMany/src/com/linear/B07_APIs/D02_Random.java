package com.linear.B07_APIs;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;
import jdk.management.resource.internal.ResourceNatives;

import java.util.Random;
import java.util.Scanner;

/*
    7.2、Random
        用来产生随机数


 */
public class D02_Random {

    public static void main(String[] args){
        System.out.println("----------");
        run01();

        System.out.println("----------");
        run02();

        System.out.println("----------");
        run03();

        System.out.println("----------");
        run04();

    }
    // 1、直接获取随机数
    // 这里注意，在java中，int占4B，范围是-2147483648 ~ 2147483647
    //      short占2B，long占8B
    public static void run01(){
        Random ra = new Random();

        // 获取一个随机int，值为int的最大值和最小值（这里注意）
        int num = ra.nextInt();

        System.out.println("随机数是：" + num);
    }

    // 2、限制随机数范围
    public static void run02(){
        Random r = new Random();

        int i = r.nextInt(10); // 返回0-9的随机数
        System.out.println(i);
    }

    // 3、随机范围偏移，[0,10)，[1,11)
    public static void run03(){
        Random r = new Random();
        int j = r.nextInt(10)+1;

        System.out.println(j);
    }

    // 4、猜数游戏
    public static void run04(){
        Random r = new Random();

        int target = r.nextInt(10) + 1;
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("输入猜测数字：");
            int guessNum = sc.nextInt();

            if(guessNum > target){
                System.out.println("太大了，请重试");
            }
            else if(guessNum < target){
                System.out.println("太小了，请重试");
            }else {
                System.out.println("猜中了！");
                break;
            }

        }
    }

}
