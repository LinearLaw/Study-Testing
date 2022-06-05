package com.example.demo.NIO_CHAT.client;

import java.io.IOException;

public class BClient {
    public static void main(String[] args) throws IOException {
        try {
            ChatClient.start("Client B");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
