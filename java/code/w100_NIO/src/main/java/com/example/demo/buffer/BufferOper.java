package com.example.demo.buffer;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
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
//            slice();
//            readonly();
//            allo();
            map();
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

    /**
     二、只读缓冲区
     */
    public static void readonly()throws Exception{
        // 初始化
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for(int i = 0;i<buffer.capacity();i++){
            buffer.put((byte)i);
        }

        // 从原buffer中派生出只读buffer
        ByteBuffer rb = buffer.asReadOnlyBuffer();

        // 往只读buffer中写入，会直接报错
        try{
            rb.put(1,(byte)123);
        }catch(Exception e){
            System.out.println(e);
        }

        // 只读
        rb.rewind();
        while(rb.hasRemaining()){
            System.out.println(rb.get());
        }
    }

    /**
    三、直接缓冲区
     可能的优化，会避免将缓冲区内容拷贝到一个中间缓冲区；
        可以让两个channel直连；
     用法和普通buffer没有区别
     */
    public static void allo()throws Exception{
        RandomAccessFile f1 = new RandomAccessFile(prefix + "/001.txt", "rw");
        RandomAccessFile f2 = new RandomAccessFile(prefix + "/003.txt", "rw");

        FileChannel c1 = f1.getChannel();
        FileChannel c2 = f2.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while(true){
            buffer.clear();

            int r = c1.read(buffer);
            if(r == -1){
                break;
            }
            buffer.flip();
            c2.write(buffer);
        }

        f1.close();
        f2.close();

    }

    /**
    四、内存映射文件IO
     让文件数据直接映射到内存数组中
     */
    public static void map()throws Exception{
        int start = 0;
        int size = 1024;

        RandomAccessFile file = new RandomAccessFile(prefix + "/001.txt", "rw");
        FileChannel fc = file.getChannel();
        MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_WRITE, start, size);

        buffer.put(0, (byte) 99);
        buffer.put(1023,(byte) 122);
        file.close();
    }


}
