package com.linear;

public class Person {

    String name;
    int age;

    public Person(String n,int a){
        name = n;
        age = a;
    }

    @Override
    public String toString(){
        return "Person {" + "name : " + name+ ", age:" + age + "}";

    }
}
