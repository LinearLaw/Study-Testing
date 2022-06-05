package com.example.demo.NIO_CHAT.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {

    }

    // 发送一个消息
    public static void start(String name) throws IOException {
        // 打开一个连接，连接到服务器
        SocketChannel socketChannel = SocketChannel.open(
                new InetSocketAddress("127.0.0.1", 8888)
        );

        // Tips：我们既需要接收，也需要发送，新开一个子线程来接收服务器的返回信息
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 创建接收信息的线程
        new Thread(new ClientThread(selector)).start();

        // 输入一段字符串，发送给服务器
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String msg = in.next();

            if(msg.length() > 0){
                socketChannel.write(Charset.forName("UTF-8").encode(name + ": " + msg));
            }
        }
    }
}
