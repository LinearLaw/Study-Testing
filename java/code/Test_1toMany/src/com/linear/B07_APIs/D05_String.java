package com.linear.B07_APIs;

/*
    7.5、String
        注意String的特性：不可改变

 */
public class D05_String {

    public static void main(String[] args){
        System.out.println("--");
        run01();


    }
    // 7.5.1、初始化
    public static void run01(){
        // 普通初始化
        String a = "";
        System.out.println("empty -> " + a);

        // 使用char数组初始化
        char[] aa = {'A','B','C'};
        String b = new String(aa);
        System.out.println("char array ->" + b);

        // 使用 byte 初始化，数字会被看做是ASCII码
        byte[] cc = {97,98,99};
        String c = new String(cc);
        System.out.println("byte array ->" + c);

        // 直接创建
        String dd = "world";
        System.out.println(dd);
    }

    /* 7.5.2、字符串常量值
        程序中直接用双引号定义的字符串，会生成到字符串常量池中。
        多个常量值他们会相等。
     */
    public static void run02(){
        String str1 = "abc";
        String str2 = "abc";
        System.out.println(str1 == str2); // true

        char[] c = {'a','b','c'};
        String str3 = new String(c);
        System.out.println(str3 == str1);  // false
    }

    /*  7.5.3、方法
         boolean b = str1.equals(str2)
         int a = str1.length()
         String str4 = str1.concat(str3)  str1和str3拼接

         str5.charAt(index);
     */
    public static void run03(){


    }

}
