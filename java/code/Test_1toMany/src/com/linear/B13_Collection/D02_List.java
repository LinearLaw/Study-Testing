package com.linear.B13_Collection;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
    13.2、 List 有序表，允许重复元素
    Collection
        - List √  允许重复元素，有序
            - ArrayList
            - LinkedList

        - Set   不允许重复元素，无序
            - HashSet
            - TreeSet

    Map
        - HashMap
        - HashTable
        - TreeMap

 */
public class D02_List {
    public static void main(String[] args){
        System.out.println("________________________________________");
        run01();

        System.out.println("________________________________________");
        run02();

    }
    /*
        1、List - ArrayList 动态数组
        主要是添加了一系列和index有关的特有方法
            public void add(int index, E element);
            public E get(int index)
            public E set(int index, E element)
            public E remove(int index);
     */
    public static void run01(){
        List<String> li = new ArrayList<>();
        li.add("a");
        li.add("b");
        li.add("c");
        li.add("d");
        li.add("e");
        System.out.println(li + "\n");

        // 1、add(index, element) 在index的地方添加元素，原有位置向后移
        System.out.println("-----【ArrayList】 add(index,element)");
        li.add(3,"536534");
        System.out.println(li + "\n");

        // 2、set(index, elem) 将index所在位置元素替换
        System.out.println("-----【ArrayList】 set");
        li.set(3,"333");
        System.out.println(li + "\n");

        // 3、remove(index)
        System.out.println("-----【ArrayList】 remove");
        String ra = li.remove(3);
        System.out.println(ra);
        System.out.println(li + "\n");
    }

    /*
        2、List - LinkedList 链表
            查询慢，增删快
            里面包含了大量的首尾操作的方法
            - 头插    public void addFirst(E e)
            - 尾插    public void addLast(E e)
            - 获取头结点     public E getFirst()
            - 获取尾结点     public E getLast()
            - 移除头结点     public removeFirst()
            - 移除尾结点     public removeLast()

            - 弹出元素 - 默认是first  public E pop(E e)
            - 插入元素 - 默认是first  public void push(E e)

            - 判断空   public boolean isEmpty()
     */
    public static void run02(){
        LinkedList<String> linked = new LinkedList<>();
        linked.add("a");
        linked.add("b");
        linked.add("c");
        linked.add("d");
        linked.add("e");
        System.out.println(linked);

        // 1、删除一个元素，默认是删除 first 指向的位置
        System.out.println("-----【LinkedList】 pop");
        String p = linked.pop();
        System.out.println(p);  // 注意，pop是删除first的位置
        System.out.println(linked + "\n");


        // 插入一个元素 - 默认是插入到 first前的位置
        System.out.println("-----【LinkedList】 push");
        linked.push("a");
        System.out.println(linked + "\n");


        // 3、获取第一个元素和最后一个元素
        System.out.println("-----【LinkedList】 getFirst getLast");
        String fi = linked.getFirst();
        String la = linked.getLast();
        System.out.println("first = "+fi + "   last = " + la + "\n");

        /* 4、addFirst == push
            addLast
        */
        System.out.println("-----addFirst addLast");
        linked.addFirst("12344");
        System.out.println("add first -> " + linked);

        linked.addLast("332255");
        System.out.println("add last -> " + linked);

        // Q : 从中间插入怎么做？ add(index, elem)
        linked.add(2,"666");
        System.out.println("add in middle -> " + linked);

        // Q : 从中间删除怎么做？ E e = remove(index)
        String rm = linked.remove(2);
        System.out.println(rm);
        System.out.println("remove in middle -> " + linked);

    }
}
