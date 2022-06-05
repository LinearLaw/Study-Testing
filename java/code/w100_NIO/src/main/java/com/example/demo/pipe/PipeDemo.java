package com.example.demo.pipe;


import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 pipe
 */
public class PipeDemo {
    public static void main(String[] args) {
        try {
            calc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     1、创建pipe
     2、往sin channel写数据
     3、从source channel读数据
     */
    public static void calc()throws Exception {
        Pipe pipe = Pipe.open();

        Pipe.SinkChannel sin = pipe.sink();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("xxxx".getBytes());
        buffer.flip();

        // 往sin channel写入数据
        sin.write(buffer);


        // 从source channel读出数据
        Pipe.SourceChannel source = pipe.source();
        ByteBuffer buffer1 = ByteBuffer.allocate(1024);

        int len = source.read(buffer1);
        System.out.println(new String(buffer1.array(), 0 , len));

        // 关闭通道
        sin.close();
        source.close();

    }
}
