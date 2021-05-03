package com.linear.B17;

public class D01_MyThread extends Thread {

    @Override
    public void run(){

        // 打印当前线程的名字
        System.out.println(Thread.currentThread().getName());
    }

    public D01_MyThread(){}
    // 当传入了一个name时，交给父类去处理
    public D01_MyThread(String name){
        super(name);
    }
}
