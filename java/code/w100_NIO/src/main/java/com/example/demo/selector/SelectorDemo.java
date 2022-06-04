package com.example.demo.selector;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 引入了Selector之后，监听的基本步骤
 1、创建Selector
 2、创建ServerSocketChannel，绑定端口，设置非阻塞模式
 3、channel注册到selector中，监听accept连接事件。
 4、循环，每一次循环调用selector.select()，检测就绪状态
 5、用selectKeys()获取就绪channel集合
 6、遍历channel集合，判断就绪事件类型，实现具体业务
 7、根据业务，决定是否需要再次注册监听事件，重复步骤二；
 */
public class SelectorDemo {

    @Test
    public void selector()throws Exception{
        Selector selector = Selector.open();

        // channel，绑定端口，设置非阻塞
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1", 1234));
        ssc.configureBlocking(false);

        // 注册，ssc需要等待一个accept，等待一个连接进入
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        // 读写的buffer
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

        writeBuffer.put("received".getBytes());
        writeBuffer.flip();

        // 循环
        while(true){
            System.out.println("listen..." + System.currentTimeMillis());

            // 遍历，获取当前就绪key数和key集合
            int readyCnt = selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();

            Iterator<SelectionKey> it = keys.iterator();

            try {
                while(it.hasNext()){
                    SelectionKey key = it.next();
                    it.remove();

                    if(key.isAcceptable()){
                        // 1、有连接接入，但是数据不一定传输完成，注册一个read事件
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }else if(key.isReadable()){
                        // 2、可读就绪，准备读取接收到的信息
                        SocketChannel channel = (SocketChannel) key.channel();
                        readBuffer.clear();

                        // 读入获取的数据
                        channel.read(readBuffer);
                        readBuffer.flip();
                        System.out.println("Received: " + new String(readBuffer.array()));

                        // 用完之后，标记为写  -> 收到了信息，然后等可写就绪的时候返回信息
                        key.interestOps(SelectionKey.OP_WRITE);
                    }else if(key.isWritable()){
                        // 3、可写就绪，准备写入需要返回的信息
                        writeBuffer.rewind();

                        SocketChannel channel = (SocketChannel) key.channel();
                        channel.write(writeBuffer);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getStackTrace());
                e.printStackTrace();
            }
        }
    }

    @Test
    public void send() throws Exception{
        SocketChannel socketChannel = SocketChannel.open();

        // 连接到1234端口
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 1234));

        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

        writeBuffer.put("hello, this is client".getBytes());
        writeBuffer.flip();

        while(true){
            // 将信息发出去
            writeBuffer.rewind();
            socketChannel.write(writeBuffer);
            readBuffer.clear();

            // 读取返回的信息
            socketChannel.read(readBuffer);
            System.out.println("client receive: " + new String(readBuffer.array()));

            Thread.sleep(1000);
        }
    }
}
