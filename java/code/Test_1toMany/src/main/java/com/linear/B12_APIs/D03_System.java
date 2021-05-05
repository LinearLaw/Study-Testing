package com.linear.B12_APIs;

/*
    12.3 System
        currentTimeMills
        arraycopy  将数组中指定的数据拷贝到另一个数组中

 */

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class D03_System {

    public static void main(String[] args){
        run01();
        System.out.println("-----------");

        run02();
        System.out.println("-----------");

        System.out.println("-----------");

    }

    // currentTimeMills
    private static void run01(){
        long t = System.currentTimeMillis();
        System.out.println(t);  // 返回当前的时间戳
    }

    /* arraycopy  将数组a从index1开始的len个元素，复制到数组b的index2开始的位置中
         源数组
         源数组开始索引
         目标数组
         目标数组的开始索引
         要拷贝的元素个数
    */
    private static void run02(){
        int[] a = {12,3,4,256,45,234,235};
        int[] b = {342,4,6,34,46,546,23,53473};

        System.arraycopy(
                a,
                0,
                b,
                2,
                3
        );
        for (int i = 0; i < b.length;i++){
            System.out.println(b[i]);
        }
    }

}
