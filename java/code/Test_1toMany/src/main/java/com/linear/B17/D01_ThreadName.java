package com.linear.B17;

/*
    17.1、获取线程的name

 */
public class D01_ThreadName {

    public static void main(String[] args){

        System.out.println("-------------");
        run01();

        System.out.println("-------------");
        run02();

        System.out.println("-------------");
        run03();
    }
    public static void run01(){
        /* 17.1.1  创建多个线程，然后看他们打印的name
         *      结果：main
                    Thread-2
                    Thread-0
                    Thread-1
         */
        D01_MyThread mt = new D01_MyThread();

        mt.start();

        new D01_MyThread().start();
        new D01_MyThread().start();

        System.out.println(Thread.currentThread().getName()); // main
    }

    public static void run02(){
        /* 17.1.2  创建多个线程，创建的同时给线程设置名称 */
        D01_MyThread mt = new D01_MyThread();
        mt.setName("【run02】时崎狂三线程");
        mt.start();

        new D01_MyThread("【run02】什么鬼线程").start();
    }

    public static void run03(){
        /* 17.1.3  创建多个线程，令线程sleep
         *   注意sleep函数传入时间以毫秒计
         *      结果：依次打印了 0 1 2 3 4
         *      sleep阻塞了线程的执行，for循环被阻塞了，因此依次打印i的值
         *      这一点和js有本质不同，因为js单线程，定时操作是异步的
         */
        for(int i = 0;i < 5; i++){
            System.out.println(i);
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }

    }
}
