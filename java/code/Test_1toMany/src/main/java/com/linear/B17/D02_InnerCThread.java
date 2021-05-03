package com.linear.B17;

/*
    17.2 匿名内部类 + 线程

 */
public class D02_InnerCThread {

    public static void main(String[] args){
        run01();

    }

    public static void run01(){
        /* 17.2.1使用匿名内部类来实时创建线程 */

        // 匿名内部类直接创建线程
        new Thread(){
            @Override
            public void run(){
                for(int i = 0;i < 5;i++) {
                    System.out.println(Thread.currentThread().getName() + "--> Thread");
                }
            }
        }.start(); // 创建后立即start，因为是匿名的


        // 具名类创建Runnable
        Runnable r = new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "--> Runnable");

                }
            }
        };
        new Thread(r).start();

        // 将Runnable和Thread都进行匿名
        new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i< 5;i++){
                    System.out.println(Thread.currentThread().getName() + "--> R T");
                }
            }
        }).start();

    }
}
