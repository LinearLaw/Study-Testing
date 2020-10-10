package com.linear.study.mybatis.utils;

import com.linear.study.mybatis.config.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 创建数据源
 */
public class DataSourceUtil {

    /**
     * 用于获取一个连接
     * @param cfg
     * @return
     */
    public static Connection getConnection(Configuration cfg){
        try {
            Class.forName(cfg.getDriver());
            return DriverManager.getConnection(
                    cfg.getUrl(),
                    cfg.getUsername(),
                    cfg.getPassword()
            );
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
