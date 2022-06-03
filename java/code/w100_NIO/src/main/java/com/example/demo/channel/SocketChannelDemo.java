package com.example.demo.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {
    public static void main(String[] args) {
        try {
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void send() throws Exception{
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(1234));

        socketChannel.configureBlocking(false);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer);

        System.out.println("client receive: " + byteBuffer.get());
        socketChannel.close();

        System.out.println("read over");

    }


}
