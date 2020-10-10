package com.linear.study.mybatis.sqlsession;

import com.linear.study.mybatis.config.Configuration;
import com.linear.study.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.linear.study.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    /**
     * 输入一个xml的文件输入流，返回一个SqlSessionFactory工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
