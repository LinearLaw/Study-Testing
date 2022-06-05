package com.example.demo.NIO_CHAT.client;

import java.io.IOException;

public class AClient {
    public static void main(String[] args) throws IOException {

        try {
            ChatClient.start("Client A");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
