package com.linear.test;

import com.linear.Thread.Ticket;

/*
    测试Thread多线程

 */
public class Test01Thread {

    public static void main(){
        Ticket tick = new Ticket();

        Thread t1 = new Thread(tick,"thread 1");
        Thread t2 = new Thread(tick, "thread 2");
        Thread t3 = new Thread(tick, "thread 3");

        t1.start();
        t2.start();
        t3.start();

    }
}