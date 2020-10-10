package com.linear.study.mybatis.sqlsession.defaults;

import com.linear.study.mybatis.config.Configuration;
import com.linear.study.mybatis.sqlsession.SqlSession;
import com.linear.study.mybatis.utils.DataSourceUtil;
import org.apache.ibatis.binding.MapperProxy;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg){
        this.cfg = cfg;

        connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 创建代理对象
     * @param daoInterfaceClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return new Proxy.newProxyInstance(
                daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},
                new MapperProxy(cfg.getMappers(),connection)
        );
    }

    /**
     * 释放连接
     */
    @Override
    public void close() {
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
