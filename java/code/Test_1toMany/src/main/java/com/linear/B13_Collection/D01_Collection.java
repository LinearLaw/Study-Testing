package com.linear.B13_Collection;

import java.lang.reflect.Array;
import java.util.*;

/*
    13、集合
        - Collection 单列集合
            - List
                - ArrayList
                - LinkedList
            - Set
                - HashSet
                - TreeSet

        - Map   双列集合
            - HashMap
                - ConcurrentHashMap
            - HashTable
            - TreeMap

 */
public class D01_Collection {

    public static void main(String[] args){
        System.out.println("__________");
        run01();

        System.out.println("__________");
        run02();

        System.out.println("__________");
        run03();

    }
    /*
        1、Collection 常用方法
            public boolean add(E e)
            public boolean remove(E e)
            public boolean contains(E e)
            public boolean isEmpty()
            public int size()
            public Object[] toArray()
            public boolean clear()
     */
    public static void run01(){
        // 创建一个Collection
        Collection<String> coll = new HashSet<>();
        System.out.println(coll); // []

        // add 插入一条数据，返回结果
        boolean b1 = coll.add("abama");
        System.out.println("add(abama) = " + b1);
        System.out.println(coll); // [Abama]

        System.out.println("-----add-----");
        // add 插入多条数据
        coll.add("BB");
        coll.add("CC");
        coll.add("DD");
        coll.add("EE");
        System.out.println(coll);


        System.out.println("-----remove-----");
        // remove 删除某一个元素
        boolean b2 = coll.remove("BB");
        System.out.println("remove(BB) = " + b2);

        boolean b3 = coll.remove("DD");
        System.out.println("remove(DD) = " + b3);
        System.out.println(coll);


        System.out.println("-----contains-----");
        // contains 判断元素是否存在
        boolean b4 = coll.contains("EE");
        System.out.println("contains(EE) = " + b4);


        // isEmpty 判断集合是否为空
        boolean b5 = coll.isEmpty();
        System.out.println("isEmpty = " + b5);


        // size 返回集合长度
        int b6 = coll.size();
        System.out.println("size = "+ b6);


        // toArray 转成对象数组
        Object[] arr = coll.toArray();
        for (int i = 0; i< arr.length; i++){
            System.out.println("    arr[" + i + "] = " + arr[i]);
        }

        // clear 清空集合
        coll.clear();
        System.out.println(coll);
        System.out.println("    isEmpty = "+ coll.isEmpty());
    }

    /*
        2、遍历Collection
            - 使用迭代器遍历
                注意一个问题，
                    如果是先获取迭代器，然后新增了元素，原有迭代器会失效。
                    如果是先新增元素，再去获取迭代器，此时可行。
            - 使用for遍历

     */
    public static void run02(){
        Collection<String> abcc = new HashSet<>();

        abcc.add("1231");
        abcc.add("867876");
        abcc.add("4444");
        abcc.add("7777");
        abcc.add("9999");

        // 获取迭代器，遍历
        Iterator<String> iter = abcc.iterator();
        while(iter.hasNext()){
            String te = iter.next();
            System.out.println("iterator -> "+te);
        }

        // 直接用for循环遍历
        for(String s : abcc){
            System.out.println("for -> "+s);
        }
    }

    /*
        3、案例 - 发牌
            - 准备牌
            - 洗牌
            - 发牌
            - 看牌

     */
    public static void run03(){

        // 花色
        String[] colors = {"♠","♥","♦","♣"};
        // 牌面
        String[] numbers = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

        // 生成52张牌
        ArrayList<String> poker = new ArrayList<>();
        for(String num: numbers){
            for(String col : colors){
                poker.add(col + " " + num );
            }
        }

        // 洗牌，shuffle函数可以将牌打乱
        Collections.shuffle(poker);

        // 3个玩家
        ArrayList<String> p1 = new ArrayList<>();
        ArrayList<String> p2 = new ArrayList<>();
        ArrayList<String> p3 = new ArrayList<>();

        // 底牌
        ArrayList<String> ground = new ArrayList<>();

        // 发牌
        for (int i = 0; i < poker.size(); i ++){
            // 抽一张牌
            String p = poker.get(i);

            // 先发底牌
            if(i >= 51){
                ground.add(p);
            }else{
                int tag = i % 3;
                switch(tag){
                    case 0:
                        p1.add(p);
                        break;
                    case 1:
                        p2.add(p);
                        break;
                    case 2:
                        p3.add(p);
                        break;
                    default:break;
                }
            }

        }

        // 看牌
        System.out.println("底牌： " + ground);
        System.out.println("p1： " + p1);
        System.out.println("p2： " + p2);
        System.out.println("p3： " + p3);

    }



}
