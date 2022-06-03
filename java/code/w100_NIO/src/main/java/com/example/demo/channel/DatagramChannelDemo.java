package com.example.demo.channel;

import org.junit.Test;

import javax.xml.crypto.Data;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

/**
 Datagram使用UDP来发送数据。
 */
public class DatagramChannelDemo {
    public static void main(String[] args) {

    }

    /**
    一、发送数据
     */
    @Test
    public void sendDatagram()throws Exception{
        DatagramChannel channel = DatagramChannel.open();

        InetSocketAddress sendAddress = new InetSocketAddress("127.0.0.1", 9999);

        while(true){
            ByteBuffer buffer = ByteBuffer.wrap("A->B 发送数据".getBytes("UTF-8"));

            channel.send(buffer, sendAddress);
            System.out.println("send success");

            Thread.sleep(1000);
        }
    }

    /**
     二、接收数据
     */
    @Test
    public void receiveDatagram()throws Exception{
        DatagramChannel receiveChannel = DatagramChannel.open();
        InetSocketAddress receiveAddress = new InetSocketAddress(9999);

        // channel和address进行bind，当前9999可以收数据
        receiveChannel.bind(receiveAddress);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(true){
            buffer.clear();

            // 接收数据，往buffer里面写
            SocketAddress socketAddress = receiveChannel.receive(buffer);
            // 写 -> 读
            buffer.flip();
            System.out.println(socketAddress.toString());
            System.out.println(">>>receive: " + Charset.forName("UTF-8").decode(buffer));
        }
    }


    /**
    三、connect后进行发送数据

     UDP不存在真正意义上的连接，
     这里的connect是指向特定服务地址用read和write接收和发送数据包
     */
    @Test
    public void connect()throws Exception{
        DatagramChannel channel = DatagramChannel.open();

        channel.bind(new InetSocketAddress(9998));
        channel.connect(new InetSocketAddress("127.0.0.1", 9999));

        channel.write(ByteBuffer.wrap("connect and send info".getBytes("UTF-8")));

        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        while(true){
            readBuffer.clear();

            // 接收来自9999的数据
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println(Charset.forName("UTF-8").decode(readBuffer));
        }
    }
}
