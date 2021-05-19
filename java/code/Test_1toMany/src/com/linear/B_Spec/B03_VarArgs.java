package com.linear.B_Spec;

/*
    Spec.3、可变参数
        JDK1.5之后出现的特性
        对于一个函数，参数的类型已经确定，但是参数的个数不确定
        此时就可以用可变参数


 */
public class B03_VarArgs {

    public static void main(String[] args){
        System.out.println("________________");
        run01();

        System.out.println("________________");
        run02();

        System.out.println("________________");


        System.out.println("________________");


    }

    /*
        1、基本格式
            使用 int...arr 指代多个int类型的参数，可以看做是一个数组
     */
    public static void run01(){
        // 传不同个数的参数，都可以用同一个函数来实现
        int s = add(423,654,5,23,56,456,2,3,46,7);
        int sa = add(4,34,3);

        System.out.println(s + "   " + sa);
    }
    // int...arr -> 看做是一个数组
    private static int add(int...arr){
        int sum = 0;
        for(int i : arr){
            sum += i;
        }
        return sum;
    }
    /*
        2、注意
            一个方法只能有一个可变参数
            多个类型的方法，可变参数要放到末尾
     */
    public static void run02(){
        int s = ada("AAA",234,54,5,467,5,523,44);
        System.out.println(s);
    }
    private static int ada(String name, int...arr){
        int sum = 0;
        for (int i:arr){
            sum+=i;
        }
        System.out.println("name = " + name);
        return sum;
    }

    // 终极写法，兼容所有对象
    public static void adb(Object...arr){

    }

}
