package com.linear.W29_JDBC_Pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
    30.7、使用C3P0来创建连接池

 */
public class JdbcUtils {

    private JdbcUtils(){}

    /**
     *  使用C3P0来创建连接池，
     *      在new ComboPooledDataSource时，C3P0会自动读取配置文件，
     *      创建多个连接，将其放入到连接池中。
     */
    private static ComboPooledDataSource dataSource;
    static{
        dataSource = new ComboPooledDataSource();
    }

    // 获取连接池
    public static DataSource getDataSource(){
        return dataSource;
    }

    // 获取一个连接
    public static Connection getConnection()throws Exception{
        return dataSource.getConnection();
    }

    // 释放流
    public static void close(ResultSet rs, Statement st, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement st, Connection conn){
        close(null,st,conn);
    }

}
