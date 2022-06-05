package com.example.demo.asynchronous;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
AsynchronousFileChannel
 异步文件通道，用异步的方式来读取文件。
 需要配合异步回调或者等待机制来获取最终读取的结果。
 1、Future，开启异步文件通道来读取后，等待Future进入Done；
 2、CompletionHandler，用回调函数来获取结果；

 */
public class AsynchronousFileChannelDemo {
    public static void main(String[] args) {
        try {
//            future();

//            completion();

//            futureWrite();

            completionWrite();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     一、future模式 -> 读
     asynchronousFileChannel，调用read或者write，返回值是一个Future对象；
     */
    public static String prefix = "w100_NIO/misc";
    public static void future() throws Exception{
        Path path = Paths.get(prefix + "/001.txt");

        AsynchronousFileChannel fileChannel = null;
        fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;

        // 直接read，返回一个future对象。
        Future<Integer> oper = fileChannel.read(buffer, position);
        while(!oper.isDone()){
            // 自旋，等待IO结束
        }

        buffer.flip();
        System.out.println(new String(buffer.array()));

        // 关闭资源
        buffer.clear();
        fileChannel.close();
    }

    /**
    二、回调模式 -> 读
     CompletionHandler 实现其completed和failed方法
     */
    public static void completion() throws Exception{
        Path path = Paths.get(prefix + "/001.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
                path,
                StandardOpenOption.READ
        );

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int position = 0;
        fileChannel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result: " + result);

                attachment.flip();
                System.out.println(new String(attachment.array()));

                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("read error");
            }
        });
    }

    /**
    三、future 写
     */
    public static void futureWrite() throws Exception{
        Path path = Paths.get(prefix + "/003.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
                path,
                StandardOpenOption.WRITE
        );

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;
        buffer.put("12312412431221".getBytes());
        buffer.flip();

        Future<Integer> oper = fileChannel.write(buffer, position);
        buffer.clear();
        while(!oper.isDone()){
            // 自旋，等待结束
        }


        System.out.println("write over");
    }

    /**
    四、completion 写

     */
    public static void completionWrite()throws Exception{
        Path path = Paths.get(prefix + "/003.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(
                path,
                StandardOpenOption.WRITE
        );

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("asdfsfsdfsdfsdfsdfds".getBytes());
        buffer.flip();
        long position = 0;
        fileChannel.write(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("write result: " + result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("error: " + exc);
            }
        });
    }
}
