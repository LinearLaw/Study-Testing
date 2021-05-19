package com.linear.W25_Junit_Ref_Anno;


/*
    25.2、反射测试用类
 */
public class D02_TestClass {
    private String name;
    private int age;

    // 4种权限成员
    public String a;
    protected String b;
    String c;
    private String d;

    public D02_TestClass(){}
    public D02_TestClass(String n, int a){
        this.name = n;
        this.age = a;
    }

    public void eat(){System.out.println("eat");}
    public void eat(String food){System.out.println("eat"+ food);}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "D02_TestClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                '}';
    }
}
