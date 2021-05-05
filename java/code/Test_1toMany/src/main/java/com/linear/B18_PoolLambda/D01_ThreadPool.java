package com.linear.B18_PoolLambda;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
    18.1、创建一个线程池

 */
public class D01_ThreadPool {

    public static void main(String[] args){
        run01();

    }
    public static void run01(){
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                5,
                20,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(5)
        );

        for (int i = 0;i < 5; i++){
            poolExecutor.execute(new RunnableImpl());

            System.out.println("poolSize:" + poolExecutor.getPoolSize());
            System.out.println("corePoolSize:" + poolExecutor.getCorePoolSize());
            System.out.println("maximumPoolSize:" + poolExecutor.getMaximumPoolSize());
            System.out.println("queue:" + poolExecutor.getQueue().size());
            System.out.println("completedTaskCount:" + poolExecutor.getCompletedTaskCount());
            System.out.println("largestPoolSize:" + poolExecutor.getLargestPoolSize());
            System.out.println("keepAliveTime:" + poolExecutor.getKeepAliveTime(TimeUnit.SECONDS));
            System.out.println("-------------");
        }


    }

}
