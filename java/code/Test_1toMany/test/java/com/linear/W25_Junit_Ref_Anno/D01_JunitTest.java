package com.linear.W25_Junit_Ref_Anno;

import org.junit.*;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

/*
    25.1、Junit，单元测试
        测试D01_Junit

 */
public class D01_JunitTest {

    /*
        所有的@Test，在运行之前，都会执行一遍@Before
        所有的@Test，在运行之后，都会执行一遍@After

     */
    @BeforeClass
    public static void bc(){
        System.out.println("Before class run");
    }
    @Before
    public void i(){
        System.out.println("Before func run");
    }

    @Test
    public void addTest(){
        D01_Junit t1 = new D01_Junit();
        int addRes = t1.add(1,4);
        Assert.assertEquals(5,addRes);
    }

    @Test
    public void minusTest(){
        D01_Junit t2 = new D01_Junit();
        int minusRes = t2.minus(10,4);
        Assert.assertEquals(5,minusRes);
    }

    @After
    public void a(){
        System.out.println("After func run");
    }

    @AfterClass
    public static void ac(){
        System.out.println("After class run");
    }

}