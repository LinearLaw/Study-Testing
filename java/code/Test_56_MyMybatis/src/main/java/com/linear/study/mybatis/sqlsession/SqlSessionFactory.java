package com.linear.study.mybatis.sqlsession;

/**
 * SqlSession的工厂类
 */
public interface SqlSessionFactory {

    // 打开一个新的SqlSession对象
    SqlSession openSession();
}
