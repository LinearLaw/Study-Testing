package com.linear.B07_APIs;

/*
    7.7、Math

 */
public class D07_Math {

    public static void main(String[] args){
        System.out.println("_________");
        run01();

    }

    // 1、abs   ceil   floor   round
    public static void run01(){
        double abb = -5435.66;

        System.out.println(abb);

        // abs=取绝对值
        System.out.println("abs = " + Math.abs(abb));

        // floor=向下取整  ceil=向上取整
        System.out.println("floor = " + Math.floor(abb));
        System.out.println("ceil = " + Math.ceil(abb));

        // round=四舍五入取整
        System.out.println("round = "+ Math.round(abb));
    }
}
