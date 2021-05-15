package com.linear.B12_APIs;

/*

    12.4、String Builder

 */

public class D04_StringBuilder {

    public static void main(String[] args){

        System.out.println("---------");
        run01();

        System.out.println("---------");
        run02();

        System.out.println("---------");
        run03();

        System.out.println("---------");
        run04();
    }

    // 1、构造
    public static void run01(){
        // 空构造
        StringBuilder bu1 = new StringBuilder();
        System.out.println("bu1: "+bu1);

        // 带字符串构造
        StringBuilder bu2 = new StringBuilder("abababa");
        System.out.println("bu2" + bu2);

    }
    // 2、常用API
    public static void run02(){
        StringBuilder bu = new StringBuilder();

        // append，进行添加数据
        bu.append("aaabbbccc");

        /* 对于一个String来说，有些方法，其实StringBulider不能用
        *   toUpperCase()
        *   toLowerCase()
        *
        *   要用这些方法，就要用先给StringBuilder 进行 toString()
        * */

        System.out.println("aaaa".toLowerCase());

        /* String函数支持链式编程
            链式编程，原因是在String函数的末尾，
                返回了String对象本身，所以可以链式调用
        */
        System.out.println(bu.toString().toUpperCase());

    }

    // 3、其他API
    public static void run03(){
        StringBuilder sb = new StringBuilder("123");

        char a = sb.charAt(2);
        System.out.println(a);

    }

    // 4、插播，包装类 - 稍微注意下
    public static void run04(){
        String a = Integer.toBinaryString(12312);

        System.out.println(a);

    }
}
