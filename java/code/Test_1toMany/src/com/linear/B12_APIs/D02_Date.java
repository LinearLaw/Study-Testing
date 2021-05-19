package com.linear.B12_APIs;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
    12.2、Date
            getTime()

        DateFormat
            - SimpleDateFormat
                parse
                format
        Calendar

 */
public class D02_Date {

    public static void main(String[] args){
        run01();
        System.out.println("------------");

        run02();
        System.out.println("------------");

        run03();
        System.out.println("------------");

    }

    // Date
    private static void run01(){
        // 返回一个时间戳
        Date d = new Date();
        long time = d.getTime();
        System.out.println(time);
        System.out.println(d.getSeconds()); // 这些方法似乎已经过时了

        // 传入一个long类型的时间戳
        Date d2 = new Date(0L);
        System.out.println(d2); // 1970年

        // 指定某一个时间戳
        Date d3 = new Date(3742767540068L);
        System.out.println(d3); // 2088年
    }

    // DateFormat - SimpleDateFormat
    private static void run02(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // format
        String curr = df.format(new Date());
        cprint(curr);

        // parse
        try {
            Date d = df.parse(curr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Calendar - 替代Date的方法
    private static void run03(){
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));// 2021
        System.out.println(cal.getTime());

        // 在日历上做出偏移，比如这里 年份+1
        cal.add(Calendar.YEAR,1);
        System.out.println(cal.get(Calendar.YEAR)); // 2022




    }

    private static void cprint(String a){
        System.out.println(a);
    }
}
