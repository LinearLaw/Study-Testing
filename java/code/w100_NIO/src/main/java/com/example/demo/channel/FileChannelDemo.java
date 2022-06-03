package com.example.demo.channel;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    public static void main(String[] args) {

        try {
            read();
            System.out.println("————————————————————");

            write();

            System.out.println("————————————————————");
            transfer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String prefix = "w100_NIO/misc";
    /**
    一、读
     */
    public static void read() throws Exception{
        // 1、开启一个fileChannel
        RandomAccessFile af = new RandomAccessFile(prefix  + "/001.txt","rw");
        FileChannel channel = af.getChannel();

        // file的根路径在当前开启的这个项目下；
        File ff = new File(prefix  + "/001.txt");
        System.out.println("file: " + ff.getAbsolutePath());
        System.out.println("size: " + channel.size());
        // 2、创建一个buffer
        ByteBuffer bf = ByteBuffer.allocate(1024);

        // 3、读取文件内容到buffer中
        // byteRead表示当前读取到的字节数
        int byteRead = channel.read(bf);
        while(byteRead != -1){
            System.out.println("————————————");
            System.out.println("read: " + byteRead);

            // 读写模式互换，转成读模式
            bf.flip();
            while(bf.hasRemaining()){
                System.out.print((char)bf.get());
            }
            System.out.println();
            bf.clear();
            byteRead = channel.read(bf);
        }


        // 4、读取完成，释放资源
        af.close();
        System.out.println("end");
    }

    /**
     二、写
     */
    public static void write() throws Exception{
        RandomAccessFile file = new RandomAccessFile(prefix  + "/002.txt", "rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 准备要写入的内容
        String newData = "123333333";

        buffer.clear();
        // 注意，如果newData的大小多于buffer的大小，这种方式会报错；
        buffer.put(newData.getBytes());
        buffer.flip();

        while(buffer.hasRemaining()){
            System.out.println("curr: " + buffer);
            channel.write(buffer);
        }

        channel.close();
    }

    /**
     三、从一个channel往另一个channel写入
     */

    public static void transfer()throws Exception{
        RandomAccessFile file = new RandomAccessFile(prefix + "/002.txt", "rw");
        FileChannel channel1 = file.getChannel();

        RandomAccessFile file2 = new RandomAccessFile(prefix + "/003.txt", "rw");
        FileChannel channel2 = file2.getChannel();

        // channel1 -> channel2
        long size = channel1.size();
        // 第一种，用transferFrom
        channel2.transferFrom(channel1, 0, size);

        // 第二种，用transferTo，写法相反，参数顺序也会相反
//        channel1.transferTo(0, size, channel2);

        file.close();
        file2.close();
        System.out.println("end");
    }

}
