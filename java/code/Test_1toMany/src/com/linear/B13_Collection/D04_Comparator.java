package com.linear.B13_Collection;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
    13.4、对Collection的排序
        使用工具类 Collections 的 sort 方法
        如果希望自定义比较规则，传入比较器即可

        有两种比较器方式
            - 目标类实现 Comparable 接口，并重写compareTo方法
            - 在sort方法中，第二参数传入一个Comparator匿名内部类，并重写 compare方法
 */
public class D04_Comparator {

    public static void main(String[] args){
        System.out.println("------------");
        run01();

        System.out.println("------------");
        run02();

        System.out.println("------------");
        run03();
    }

    /*
        1、普通数据类型，sort排序
            默认是从小到大排序
     */
    public static void run01(){
        ArrayList<Integer> as = new ArrayList<>();

        as.add(1234);
        as.add(34654);
        as.add(4);
        as.add(988);

        System.out.println("before : " + as);
        Collections.sort(as);
        System.out.println("after : " + as);
    }

    /*
        2、自定义数据
            直接传入 Comparator 匿名内部类，重写其compare方法
     */
    public static void run02(){
        ArrayList<B> ab = new ArrayList<>();

        B a1 = new B("truma",44);
        B a2 = new B("abama",99);
        B a3 = new B("sssaa",95);

        ab.add(a1);
        ab.add(a2);
        ab.add(a3);

        // 传入匿名内部类，重写compare方法
        System.out.println("before : " +ab);
        Collections.sort(ab, new Comparator<B>() {
            @Override
            public int compare(B o1, B o2) {
                if(o1.age == o2.age){
                    return o1.name.compareTo(o2.name);
                }
                return o1.age - o2.age;
            }
        });
        System.out.println("after : "+ ab);
    }

    /*
        3、自定义数据
            实现 Comparable 接口，重写其compareTo方法
     */
    public static void run03(){
        ArrayList<A> ab = new ArrayList<>();

        // 在自定义类A中，实现 Comparable 接口，重写compareTo方法
        A a1 = new A("truma",44);
        A a2 = new A("abama",99);
        A a3 = new A("sssaa",95);

        ab.add(a1);
        ab.add(a2);
        ab.add(a3);

        System.out.println("before : " +ab);
        Collections.sort(ab);
        System.out.println("after : "+ ab);

    }
}

class A implements Comparable<A>{
    public String name;
    public int age;
    public A(){}
    public A(String n,int a){this.name = n;this.age=a;}

    @Override
    public String toString() { return "A{name='" + name + ", age=" + age + '}';  }

    // 重写其compareTo方法，按年龄排序，年龄相同就按字符串顺序排序
    @Override
    public int compareTo(A o){
        if(o.age == this.age){return o.name.compareTo(this.name);}

        return o.age - this.age;
    }
}

class B{
    public String name;
    public int age;
    public B(){}
    public B(String n,int a){this.name = n;this.age=a;}

    @Override
    public String toString() { return "B{name='" + name + '\'' + ", age=" + age + '}'; }
}
