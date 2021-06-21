package com.linear.D04_ProxyProducer.cglib;

public class Producer {
    public void saleProduct(float money) {
        System.out.println("销售出去了产品，获得￥ " + money);
    }

    public void afaterService(float money) {
        System.out.println("售后服务，获得￥ " + money);
    }
}
