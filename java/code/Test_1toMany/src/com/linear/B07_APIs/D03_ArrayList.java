package com.linear.B07_APIs;

import com.linear.Person;

import java.util.ArrayList;

/*
    7.3、ArrayList

    - Collection
        - List
            - ArrayList  √
            - LinkedList

        - Set
            - HashSet
            - TreeSet

    多个对象，需要一个容器来装，
        如果是对象数组，对象数组的长度固定，不符合元素动态变化要求。
        所以引入ArrayList用来存储和操作对象数据

 */
public class D03_ArrayList {
    public static void main(String[] args){
        System.out.println("----------");
        run701();

        System.out.println("----------");
        run702();

        System.out.println("----------");
        run703();

        System.out.println("----------");
        run704();
    }

    // 1、原本的方法，用数组来存储多个对象
    public static void run701(){
        // 用一个对象数组，需要在初始化数组时指定数组长度
        Person[] pbox = new Person[3];

        // 创建多个对象
        Person a = new Person("AMD",30);
        Person b = new Person("AMD",30);
        Person c = new Person("AMD",30);

        // 加入到数组
        pbox[0] = a;
        pbox[1] = b;
        pbox[2] = c;

        // 直接打印数组，这里打印出来的是一个地址
        System.out.println(pbox);

        for (int i = 0;i < 3; i++){
            System.out.println(pbox[i]);
        }
        /* 有个问题，如果要加新的Person对象进去怎么办
              创建一个新的数组，设置一个更长的大小
              将原有数组移到新数组中
              将原有数组销毁

           这样显然太麻烦了
        */
    }

    /*  2、使用ArrayList
        数组长度可以变，可以使用泛型（泛型只能是引用类型，不能是基本类型）
     */
    public static void run702(){
        ArrayList<Person> list = new ArrayList<>();

        // ArrayList重写了toString方法，这里打印出来是一个 []
        System.out.println(list);

        Person a = new Person("AMD",123);
        Person b = new Person("NVIDIA",3);
        Person c = new Person("INTEL",99);

        list.add(a);
        list.add(b);
        list.add(c);

        for (int i = 0; i < list.size();i++){
            // 这里不能直接用 list[i]，而要用 list.get(i)
            System.out.println(list.get(i));
        }

        // 一个ArrayList可以直接整体toString打印
        System.out.println("Result:");
        System.out.println(list); // 这里打印 [Person{xxx},Person{xxx}]

    }

    /*  3、add   get   set   remove   size()

     */
    public static void run703(){

        ArrayList<String> ar = new ArrayList<>();

        ar.add("123");
        ar.add("321");
        ar.add("7746");
        ar.add("7744");
        ar.add("723");
        System.out.println("add : " + ar);

        System.out.println("[2] = " + ar.get(2));

        ar.set(2,"776");
        System.out.println("set : " + ar);

        ar.remove(2);
        System.out.println("remove : " + ar );

        System.out.println("size : " + ar.size());
    }
    /*  4、ArrayList的泛型，只能是引用类型，不能用基本类型
            如果希望往ArrayList中加入基本类型，例如int，需要使用int的包装类型
        byte    - Byte
        short   - Short
        int     - Integer
        long    - Long
        float   - Float
        double  - Double
        char    - Character
        boolean - Boolean

        自动装箱： 基本类型 -> 包装类型
        自动拆箱： 包装类型 -> 基本类型

     */
    public static void run704(){
        ArrayList<Integer> ar = new ArrayList<>();

        ar.add(100);    // 这里100是int类型，自动装箱，所以加入时变成Integer
        ar.add(200);
        ar.add(300);

        System.out.println(ar.get(1));
    }
}
