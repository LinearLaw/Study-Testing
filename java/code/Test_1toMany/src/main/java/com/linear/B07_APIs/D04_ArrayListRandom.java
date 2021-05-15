package com.linear.B07_APIs;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Random;

/*
    7.4、测试

 */
public class D04_ArrayListRandom {

    public static void main(String[] args){
        System.out.println("----");
        run01();

    }

    /*
        生成20个随机数，并将随机数中的偶数存到另一个集合中。
        统计出现的偶数个数

     */
    public static void run01(){
        ArrayList<Integer> box = new ArrayList<>();

        // 1.1、生成随机数，加入到arrayList中
        Random r = new Random();
        int range = 100;
        int size = 20;
        for (int i = 0;i < size;i++){
            box.add(r.nextInt(range)+1);
        }

        // 2.1、遍历集合，将偶数加入到小集合中。
        ArrayList<Integer> lbox = new ArrayList<>();
        for (int i =0; i < box.size(); i++){
            int temp = box.get(i);
            if(temp % 2 == 0) {
                lbox.add(temp);
            }
        }

        System.out.println(lbox);
        System.out.println(lbox.size());
    }
}
