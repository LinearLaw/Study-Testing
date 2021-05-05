package com.linear.B17_Thread;

/*
    17.3  waiting and awake
    生产者 - 消费者模型
        显然，多线程下需要保证线程安全

    Object 类中的方法
        void wait()
            - 其他的线程调用该对象的notify() or notifyAll()，使得当前等待
        void notify()
            - 唤醒在此对象监视器上的等待的单个线程
            - 继续执行之前wait()后的代码


 */
public class D03_Awake {

    public static void main(String[] args){
        run01();

    }
    // 创建obj作为同步锁
    final static Object oo = new Object();
    public static void run01(){

        // 创建一个消费者
        createAConsumer(1);
        // 再创建一个消费者
        createAConsumer(2);

        // 创建一个生产者
        new Thread(){
            @Override
            public void run(){
                while(true){
                    // spend time for food
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){e.printStackTrace();}

                    synchronized (oo){
                        System.out.println("[P]produce food");

                        oo.notify(); // 通知到消费者
                    }
                }

            }
        }.start();
    }
    public static void createAConsumer(final int tag){
        // 消费者
        new Thread(){
            @Override
            public void run(){
                while(true){
                    synchronized (oo){
                        System.out.println("[C-" +tag+"]consumer ask to producer for food");
                        try{
                            oo.wait();
                        }catch(InterruptedException e){ e.printStackTrace(); }

                        System.out.println("[C-" +tag+"]consumer get his food");
                        System.out.println("---------");
                    }
                }
            }
        }.start();

    }

}
