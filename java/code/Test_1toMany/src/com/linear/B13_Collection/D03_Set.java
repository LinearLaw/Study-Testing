package com.linear.B13_Collection;

import com.linear.Student;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.HashSet;
import java.util.LinkedHashSet;

/*
    13.3、Set，没有扩充List接口，反而增加了新的限制。
        元素无序，不可重复
    Collection
        - List  允许重复元素，有序
            - ArrayList
            - LinkedList

        - Set  √ 不允许重复元素，无序
            - HashSet
                - LinkedHashSet 多用了一个链表来存顺序，是有序结构
            - TreeSet

    Map
        - HashMap
        - HashTable
        - TreeMap

 */
public class D03_Set {

    public static void main(String[] args){
        System.out.println("【run01】");
        run01();

        System.out.println("\n【run02】");
        run02();

        System.out.println("\n【run03】");
        run03();

        System.out.println("\n【run04】");
        run04();
    }

    /*
        1、HashSet   哈希表，不同之处是，元素就充当了哈希值
            不能存相同元素！！！！
            哈希表，
                jdk1.8之前，使用数组 + 链表，在元素很多的情况下，查找效率会很低
                jdk1.8之后，使用数组 + 链表 + 红黑树，使用红黑树来优化查找效率
     */
    public static void run01(){
        HashSet<String> has = new HashSet<>();

        has.add("cba");
        has.add("cba"); // 即使插入了一个相同元素，实际在HashSet中只会存在一份

        has.add("cbb");
        has.add("cbc");
        has.add("cbd");

        for (String name : has){
            System.out.println(name);
        }
    }

    /*
        2、HashSet 存储自定义类型元素
            -> 需要重写hashCode函数和equals函数，
            -> set依据hashCode的结果进行计算哈希值，根据equals函数计算元素是否已存在
     */
    public static void run02(){
        HashSet<Student> hs = new HashSet<>();

        Student st1 = new Student("abama",80);
        Student st2 = new Student("trump",30);
        Student st3 = new Student("biden",50);

        hs.add(st1);
        hs.add(st2);
        hs.add(st3);

        System.out.println("st1 hash code = " + st1.hashCode());
        System.out.println("st2 hash code = " + st2.hashCode());
        System.out.println("st3 hash code = " + st3.hashCode());

        System.out.println("st1 == st2 : " + (st1 == st2));
        System.out.println("st1 equals st2 : " + st1.equals(st2));

        System.out.println("hs : " + hs);
    }

    // 3、LinkedHashSet 存储String
    public static void run03(){
        LinkedHashSet<String> hs = new LinkedHashSet<>();

        hs.add("236");
        hs.add("234");
        hs.add("235");
        hs.add("237");
        System.out.println(hs);
    }

    /* 4、LinkedHashSet 存储自定义类型
            和HashSet一样，要求自定义类型要重新实现hashCode和equals
            这个东西的排序，不知道是怎么排的
    */
    public static void run04(){
        LinkedHashSet<Student> hs = new LinkedHashSet<>();

        Student st1 = new Student("abama",80);
        Student st2 = new Student("trump",30);
        Student st3 = new Student("biden",50);

        hs.add(st1);
        hs.add(st2);
        hs.add(st3);

        System.out.println(hs);
    }
}
