package com.example.demo.NIO_CHAT.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 聊天室 - 服务端

 */
public class ChatServer {
    public static void main(String[] args) throws IOException {
        try {
            new ChatServer().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     启动服务
     1、创建Selector
     2、创建ServerSocketChannel，设置非阻塞，绑定端口，注册到selector -> acceptable
     3、循环，监听accept，等待连接接入
     4、令selector不断select，去查就绪状态
        如果有accept，获取这个socket channel，设置非阻塞，注册到selector -> 可读
        如果有可读channel，获取该socket channel，用byteBuffer读取内容。
            读取完后，将该socket channel重新注册到selector -> 后面继续通信
            将获取到的内容，广播给其他的channel
     */
    public void start() throws IOException {
        // 初始化selector
        Selector selector = Selector.open();

        // 初始化server socket
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress(8888));

        // 注册
        channel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务启动成功");
        for(;;){
            int readLength = selector.select();
            if(readLength <= 0){
                continue;
            }

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while(iterator.hasNext()){
                SelectionKey curr = iterator.next();
                iterator.remove();

                if(curr.isAcceptable()){
                    // 有连接接入
                    acceptOper(selector, channel);
                }
                if(curr.isReadable()){
                    // 连接数据已接收完毕，可以读了
                    readOper(selector, curr);
                }
            }
        }
    }



    // 收到了客户端的连接，获取这个连接，注册为读监听，并返回一条消息过去
    private void acceptOper(Selector selector, ServerSocketChannel channel) throws IOException {
        SocketChannel inChannel = channel.accept();
        inChannel.configureBlocking(false);

        inChannel.register(selector, SelectionKey.OP_READ);

        // 给连进来的客户端发一条消息
        inChannel.write(Charset.forName("UTF-8").encode("欢迎进入聊天室"));
    }

    private void readOper(Selector selector, SelectionKey selectionKey) throws IOException {
        // 获取这个可读连接
        SocketChannel channel = (SocketChannel) selectionKey.channel();

        // 用buffer读出这个信息
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int readLength = channel.read(buffer);
        String msg = "";
        if(readLength > 0){
            buffer.flip();
            msg += Charset.forName("UTF-8").decode(buffer);
        }

        // 重新注册，继续接收信息
        channel.register(selector, SelectionKey.OP_READ);

        if(msg.length() > 0){
            System.out.println("cast msg : " + msg);

            // 广播给其他人
            castOper(selector, channel, msg);
        }
    }

    private void castOper(Selector selector, SocketChannel channel, String msg) throws IOException {
        // 从selector中获取所有连接，将msg发给这些连接
        Set<SelectionKey> keys = selector.keys();
        for(SelectionKey curr : keys){
            Channel target = curr.channel();

            // msg不需要发送给自己
            if(target instanceof SocketChannel && target != channel){
                ((SocketChannel)target).write(Charset.forName("UTF-8").encode(msg));
            }
        }
    }


}
