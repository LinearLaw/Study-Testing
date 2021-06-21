package com.linear.D04_ProxyProducer.proxy;

/***
 * 57.5、动态代理 - 基于接口
 *
 */
public interface IProducer {
    /** 销售产品 */
    public void saleProduct(float money);

    /** 售后服务 */
    public void afaterService(float money);

}
