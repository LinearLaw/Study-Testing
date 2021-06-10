package com.linear.W42_Lombok;


/*
    42.1、lombok
 */

import lombok.*;
import org.junit.Test;

/**
 *  1、@Data 对类作用，
 *  可以产生getter/setter、toString、equals、hashCode、canEquals全家桶
 */
@Data
class A{
    private int id;
    private int age;
}

/**
 *  2、@Getter @Setter 对属性作用
 *  3、@ToString 作用在类上
 *
 */
@ToString
class B{
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private int age;
}

/**
 *  4、
 *  @NoArgsConstructor 无参构造器
 *  @AllArgsConstructor 全参构造器
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
class C{
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private int age;
}


public class D01_Basic {
    // @Data
    @Test
    public void testData(){
        A a = new A();

        // 这里可以看到，虽然自己没写setId方法，但是编译直接成功，没有报错
        a.setId(100);

        System.out.println(a);
    }

    // @ToString @Getter @Setter
    @Test
    public void testGetterSetter(){
        B b = new B();
        b.setId(100);
        b.setAge(200);

        System.out.println(b);
    }

    // @NoArgsConstructor 无参构造器   @AllArgsConstructor 全参构造器
    @Test
    public void testConstructor(){
        C c1 = new C();
        c1.setId(100);
        c1.setAge(200);
        System.out.println(c1);

        C c2 = new C(100,200);
        System.out.println(c2);
    }
}
