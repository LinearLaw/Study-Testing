package com.linear.study.mybatis.sqlsession.defaults;

import com.linear.study.mybatis.config.Configuration;
import com.linear.study.mybatis.sqlsession.SqlSession;
import com.linear.study.mybatis.sqlsession.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }


    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
