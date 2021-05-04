package com.linear.B17;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/*
    17.4、出现了多个条件的时候，
        wait和notify的方式，会导致出现2把锁
        这时候可以使用基于Condition的await signal机制，进行条件判断

 */
public class D04_Condition {
    /*
    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition(); // 2个条件
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public static void main(String[] args){

    }

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }*/
}
