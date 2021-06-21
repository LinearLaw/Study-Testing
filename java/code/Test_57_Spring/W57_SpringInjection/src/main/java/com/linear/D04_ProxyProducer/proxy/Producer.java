package com.linear.D04_ProxyProducer.proxy;

/***
 * 57.5、动态代理 - 基于接口
 *
 */
public class Producer implements IProducer {
    public void saleProduct(float money) {
        System.out.println("销售出去了产品，获得￥ " + money);
    }

    public void afaterService(float money) {
        System.out.println("售后服务，获得￥ " + money);
    }
}
