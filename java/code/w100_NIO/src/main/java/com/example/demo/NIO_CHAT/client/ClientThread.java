package com.example.demo.NIO_CHAT.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class ClientThread implements Runnable {
    private Selector selector;
    public ClientThread(Selector selector){
        this.selector = selector;
    }

    @Override
    public void run() {

        try {
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

                    if(curr.isReadable()){
                        // 连接数据已接收完毕，可以读了
                        readOper(selector, curr);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            System.out.println(msg);
        }
    }
}
