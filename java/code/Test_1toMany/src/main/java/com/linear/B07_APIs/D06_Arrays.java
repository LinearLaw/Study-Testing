package com.linear.B07_APIs;

import java.util.Arrays;
import java.util.List;

/*
    7.6、Arrays
        用于操作数组的工具类

 */
public class D06_Arrays {

    public static void main(String[] args){

        System.out.println("-----------------------");
        run01();

    }

    // 1、sort   asList   toString
    public static void run01(){
        int[] arr = {2,3,4,63,46745,34,7567346};

        Arrays.sort(arr);
        List li = Arrays.asList(arr);

        String sarr = Arrays.toString(arr);
        System.out.println(sarr);
    }

}
