package com.example.demo.buffer;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 对buffer的一些操作
 6、分片 slice()
 7、只读缓冲区 asReadOnlyBuffer()
 8、直接缓冲区 allocateDirect(capacity)
 9、内存映射文件IO   map()
 */
public class BufferOper {
    public static void main(String[] args) {
        try {
            slice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     一、slice()
     可以创建一个子缓冲区
     */
    public static String prefix = "w100_NIO/misc";
    public static void slice()throws Exception{
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for(int i = 0;i<buffer.capacity();i++){
            buffer.put((byte)i);
        }

        // 建立子缓冲区，设置position和limit
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer child = buffer.slice();

        // 修改子缓冲区的内容
        for(int i = 0;i<child.capacity();i++){
            byte b = child.get(i);
            // 将该字符的ASCII码值乘10倍；
            b *= 10;
            child.put(i, b);
        }

        // 恢复
        buffer.position(0);
        buffer.limit(buffer.capacity());
        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }


    public static void readonly()throws Exception{
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for(int i = 0;i<buffer.capacity();i++){
            buffer.put((byte)i);
        }
    }
}
