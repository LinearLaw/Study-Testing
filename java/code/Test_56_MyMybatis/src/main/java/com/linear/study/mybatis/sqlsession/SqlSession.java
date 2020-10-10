package com.linear.study.mybatis.sqlsession;

/**
 * 和数据库交互的核心类，用来创建dao接口的代理对象
 */
public interface SqlSession {

    /**
     * 传入一个接口，生成一个代理对象
     * @param daoInterfaceClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源
     */
    void close();
}
