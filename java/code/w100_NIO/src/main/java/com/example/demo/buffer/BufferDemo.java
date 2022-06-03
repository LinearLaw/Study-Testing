package com.example.demo.buffer;

import javax.swing.plaf.basic.BasicTextAreaUI;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 buffer的方法
 1、写入数据
 2、读取数据
 3、rewind()
 4、clear()   compact()
 5、mark()   reset()

 */
public class BufferDemo {
    public static void main(String[] args) {
        try {
//            write();
//            read();
//            clearCompact();
            markReset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String prefix = "w100_NIO/misc";

    /**
    一、给buffer写入数据，有两种方式
     第一种，从channel读出，写入到buffer
     第二种，直接用put来往buffer中写
     */
    public static void write() throws Exception{
        RandomAccessFile file = new RandomAccessFile(prefix + "/001.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 1、从channel中读出来写入buffer
        channel.read(buffer);
        buffer.flip();
        while(buffer.hasRemaining()){
            System.out.print((char)buffer.get());
        }
        System.out.println();
        System.out.println("——————————————————");

        // 2、直接用put来改buffer
        System.out.println("position: " + buffer.position());
        buffer.rewind();
        System.out.println("position: " + buffer.position());

        System.out.println("——————————————————");

        // 从该指定位置，写入一个字符串
        int offset = 10;
        byte[] arr = "112321".getBytes();
        for(int i = offset,j = 0;i<buffer.capacity();i++,j++){
            if(j >= arr.length){
                break;
            }
            buffer.put(i,arr[j]);
        }

        buffer.rewind();
        while(buffer.hasRemaining()){
            System.out.print((char)buffer.get());
        }
        System.out.println();
        System.out.println("——————————————————");

        channel.close();
    }

    /**
    二、从buffer中读取数据
     1、从buffer中读取数据到channel
     2、用get()方法从buffer中直接读取数据

     */
    public static void read() throws Exception{
        RandomAccessFile file = new RandomAccessFile(prefix + "/001.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 从channel中读取数据，写入到buffer
        channel.read(buffer);

        RandomAccessFile file2 = new RandomAccessFile(prefix + "/003.txt", "rw");
        FileChannel channel2 = file.getChannel();

        // 1、将刚才的buffer，写入到channel中
        channel2.write(buffer);

        // 2、直接用get来读buffer，该操作会改变position
        buffer.rewind();
        System.out.println("position: " + buffer.position());
        byte a = buffer.get();
        System.out.println("position: " + buffer.position());

        System.out.println("read: " + (char)a);

        channel.close();
        channel2.close();
    }

    /**
     三、clear 和 compact
        clear()和compact，主要表现在对于未读数据的处理上
        clear()暴力置0
        compact()将未读数据移到buffer前端，将position放到这部分的末尾
     */
    public static void clearCompact()throws Exception{
        RandomAccessFile file = new RandomAccessFile(prefix + "/001.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        channel.read(buffer);

        buffer.flip();
        // 只读前面100个
        int i = 0;
        while(buffer.hasRemaining() && i < 100){
            System.out.print(buffer.get());
            i++;
        }
        System.out.println();
        System.out.println("————————————————————————");

        System.out.println("after get position: " + buffer.position());
        buffer.clear();
        System.out.println("after clear position: " + buffer.position());

        System.out.println("————————————————————————");
        // 再读一次
        // 只读前面100个
        i = 0;
        while(buffer.hasRemaining() && i < 100){
            System.out.print(buffer.get());
            i++;
        }
        System.out.println();
        System.out.println("reget position: " + buffer.position());
        System.out.println("————————————————————————");

        buffer.compact();
        System.out.println("after compact position: " + buffer.position());

    }

    /**
    四、mark   reset
     mark() 用来打标记，记录当前position的值
     reset()  回到该标记
     */
    public static void markReset()throws Exception{
        RandomAccessFile file = new RandomAccessFile(prefix + "/001.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        channel.read(buffer);

        buffer.flip();

        // 只读前面100个
        int i = 0;
        while(buffer.hasRemaining() && i < 100){
            System.out.print(buffer.get());
            i++;
        }
        System.out.println();

        // mark记录一下当前位置
        buffer.mark();
        System.out.println("mark position: " + buffer.position());

        // 继续读100个
        i = 0;
        while(buffer.hasRemaining() && i < 100){
            System.out.print(buffer.get());
            i++;
        }
        System.out.println();
        System.out.println("before reset position: " + buffer.position());

        // reset，回到刚才mark的位置
        buffer.reset();
        System.out.println("after reset position: " + buffer.position());
    }
}
