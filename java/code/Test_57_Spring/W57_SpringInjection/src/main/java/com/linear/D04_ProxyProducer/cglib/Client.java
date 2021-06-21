package com.linear.D04_ProxyProducer.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

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
        Producer cglibProducer = (Producer) Enhancer.create(
                producer.getClass(),
                new MethodInterceptor() {
                    /** 代理内部增强，和基于接口的proxy是一毛一样的。 */
                    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                        Object returnValue= null;

                        Float money = (Float)args[0];
                        if("saleProduct".equals(method.getName())){
                            returnValue = method.invoke(producer, money*0.8f);
                        }
                        return returnValue;
                    }
                }
        );

        cglibProducer.saleProduct(10000f);
    }
}
