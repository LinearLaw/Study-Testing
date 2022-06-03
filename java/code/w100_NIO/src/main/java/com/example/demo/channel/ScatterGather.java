package com.example.demo.channel;

import java.nio.ByteBuffer;

public class ScatterGather {

    /**
     scatter，分散，将channel的数据分散到多个buffer中
     gather，聚集，将多个buffer的内容聚集到一个channel中
     */
    public static void main(String[] args) {

    }

    public static void scatter(){
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        ByteBuffer[] barr = {header, body};

        // 当channel读出数据，写入到barr时，会依次先写入header，再写入body
        // 这种方式，
        // 不适合消息大小不固定的情况，即动态消息，如果消息大小不够，要进行填充；
        // 适合用于固定结构的数据格式；
        // channel.read(barr);
    }

    public static void gather(){
        ByteBuffer header = ByteBuffer.allocate(128);
        ByteBuffer body = ByteBuffer.allocate(1024);

        ByteBuffer[] barr = {header, body};

        // 调用write方法，将barr的内容，写入到channel中
        // 按顺序，将数据写入到channel，有多少写多少
        // 可以用于动态消息；
        // channel.write(barr);
    }
}
