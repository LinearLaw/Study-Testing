package com.linear.B12_APIs;

public class Person {

    String name;
    int age;

    Person(String n,int a){
        name = n;
        age = a;
    }

    @Override
    public String toString(){
        return "Person {" + "name : " + name+ ", age:" + age + "}";

    }
}
