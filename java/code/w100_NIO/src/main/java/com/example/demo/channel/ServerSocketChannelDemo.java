package com.example.demo.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelDemo {
    public static void main(String[] args) {

        try {
            listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     监听
     */
    public static String greet = "hello world: from sever socket";

    public static void listen()throws  Exception{
        // 端口号
        int port = 1234;

        ByteBuffer sendBuffer = ByteBuffer.wrap(greet.getBytes());
        ByteBuffer receiveBuffer = ByteBuffer.allocate(256);

        ServerSocketChannel ssc = ServerSocketChannel.open();
        // ServerSocketChannel不能直接bind，要用一个socket来进行bind
        ssc.socket().bind(new InetSocketAddress(port));

        // 设置非阻塞模式
        ssc.configureBlocking(false);

        while(true){
            System.out.println("waiting for connection");

            // 等待连接接入，
            // 如果是阻塞模式，accept()会阻塞，直到有新连接进入
            // 如果是非阻塞模式，accept()会不停轮询；
            SocketChannel sc = ssc.accept();
            if(sc == null){
                System.out.println("no connection: " + System.currentTimeMillis());
                Thread.sleep(1000);
            }else{
                System.out.println("——————————————————");
                // 获取ip地址
                System.out.println("Incoming connection from : " + sc.socket().getRemoteSocketAddress());

                // 接信息 -> 不好使，后面再看
//                int cnt = sc.read(receiveBuffer);
//                while(cnt != -1){
//                    receiveBuffer.flip();
//                    System.out.print("server receive: ");
//                    while(receiveBuffer.hasRemaining()){
//                        System.out.print((char)receiveBuffer.get());
//                    }
//                    System.out.println();
//                }
//                System.out.println("——————————————————");

                // 发信息
                // 重置send buffer的指针，指向0
                sendBuffer.rewind();

                // 将信息发送给对面
                sc.write(sendBuffer);

                sc.close();
            }
        }
    }
}
