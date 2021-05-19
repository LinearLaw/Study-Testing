package com.linear;

import java.util.Objects;

public class Student {
    private String name;
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    private int age;
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}

    public Student(){}
    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    // 为了计算HashCode，使得Set可以正确存储，如果equals重写了，需要重写HashCode方法
    @Override
    public int hashCode(){
        return Objects.hash(name,age); // 用name和age来做哈希值的参数
    }

    // 判断两者是否相等，改为判断name和age是否相等
    @Override
    public boolean equals(Object e){
        if(this == e){return true;}

        // 判断 e 是否为空 + 判断 e 和 this 是不是同类对象
        if(e == null || getClass() != e.getClass()){ return false;}

        Student stu = (Student)e;
        return age == stu.age && Objects.equals(name, stu.name);
    }

    @Override
    public String toString() { return "Student{" + "name='" + name + '\'' + ", age=" + age + '}'; }
}
