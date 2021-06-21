package com.linear.D04_ProxyProducer.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/***
 * 57.5、动态代理 - 基于接口
 *      用户，向生产商购买产品
 *
 *      基于接口的代理，要求某一个类对象实现了某一个接口，
 *      代理后，代理对象按照接口来生成
 *
 *      如果某一个类没有实现任何接口，此时就要用cglib，基于子类进行动态代理
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("1、从厂家购买产品");
        buyFromProducer();

        System.out.println("2、从经销商处购买产品");
        buyFromProxy();
    }

    /** 1、从厂家直接购买产品 + 售后 */
    public static void buyFromProducer(){
        final Producer producer = new Producer();

        producer.saleProduct(10000f);
        producer.afaterService(1000f);
    }

    /** 2、从经销商处购买产品，经销商管售后 */
    public static void buyFromProxy(){
        final Producer producer = new Producer();

        // 创建代理对象，将当前的producer，代理到另一个对象cglibProducer
        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(
                producer.getClass().getClassLoader(),
                producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object returnValue = null;

                        Float money = (Float)args[0];
                        if("saleProduct".equals(method.getName())){
                            returnValue = method.invoke(producer, money*0.8f);
                        }
                        return returnValue;
                    }
                }
        );

        proxyProducer.saleProduct(10000f);
    }
}
