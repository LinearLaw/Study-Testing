package com.linear.B13_Collection;

import java.util.*;

/*
    13.5、Map
        用来表示映射关系
        key value 二元组
        - HashMap
        - LinkedHashMap
 */
public class D05_Map {

    public static void main(String[] args){
        System.out.println("__________________");
        run01();

        System.out.println("__________________");
        run02();

        System.out.println("__________________");
        run03();

        System.out.println("__________________");
        run04();

        System.out.println("__________________");
        run05();

        System.out.println("__________________");
        run06();
    }
    public static Map<String, Integer> generateMap(){
        Map<String,Integer> m = new HashMap<>();
        m.put("abama",60); // 注意，往map里面插入元素是用 put
        m.put("trump",70);
        m.put("biden",82);
        m.put("cden",0);
        m.put("dden",89);
        m.put("eden",67);
        m.put("sden",56);

        return m;
    }

    /*
        1、public boolean containsKey(key) 判断HashMap中是否存在某一个key
     */
    public static void run01(){
        Map<String,Integer> m = generateMap();
        boolean b1 = m.containsKey("abama"); // true
        boolean b2 = m.containsKey("truma"); // false

        System.out.println(b1);
        System.out.println(b2);
    }

    /*
        2、public V get(Object key) 根据key获取value
     */
    public static void run02(){
        Map<String,Integer> m = generateMap();
        Integer v1 = m.get("abama");
        Integer v2 = m.get("dilireba"); // 如果key值不存在，返回一个null
        System.out.println(v1 + "   "+ v2);
    }

    /*
        3、public V remove(Object key) 移除某一个key指向的键值对
     */
    public static void run03(){
        Map<String,Integer> m = generateMap();

        Integer v1 = m.remove("trump");
        System.out.println(v1);
    }

    /*
        4、遍历Map，有两个方法
            - keySet() 返回的是 Set<K>
            - entrySet()    返回的是Set<Map.Entry<K,V>>，每个元素用getKey()  getValue()
     */
    public static void run04(){
        Map<String,Integer> m = generateMap();

        // 使用 keySet 获取key值集合，然后遍历获取每一个value
        Set<String> ks = m.keySet();
        for(String k : ks){
            Integer age = m.get(k);
            System.out.println(age);
        }

        // 使用 entrySet 获取 Map.Entry 类对象，遍历获取key和value
        Set<Map.Entry<String,Integer>> sm = m.entrySet();
        for(Map.Entry<String,Integer> km : sm){
            String key = km.getKey();
            Integer val = km.getValue();
            System.out.println(key + "  : " + val);
        }
    }

    /*
        5、以自定义类型作为key
            此时，自定义类，需要重写hashCode和equals方法，保证key唯一
     */
    private static void run05(){

    }

    /*
        6、测试
            输入一个字符串，统计字符串中每个字符出现个数。
     */
    private static void run06(){
        // 输入字符
        Scanner sc = new Scanner(System.in);
        System.out.println("输入一个字符串：");
        String str = sc.next();

        // 遍历字符串，统计
        HashMap<Character, Integer> box = new HashMap<>();
        for(char c : str.toCharArray()){
            if(box.containsKey(c)){
                Integer va = box.get(c);
                box.put(c,++va);
            }else{
                box.put(c,1);
            }
        }

        // 输出
        System.out.println(box);
    }

    /*
        7、of(E...element)
        JDK 9 开始支持，of
        Tips
        - 只支持List，Set, Map，不适用于接口的实现类。
        - 返回值是一个不能改变的集合，集合不能用add，put方法添加元素，会抛出异常
        - set和map在用of初始化时，不能有重复元素，否则抛异常
     */
    private static void run07(){

    }

}
