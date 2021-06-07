package com.linear.W34_login.Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
    34、连接池工具类 - 基于C3P0
 */
public class C3P0Utils {

    private static DataSource dataSource= new ComboPooledDataSource();

    /**
     * 获取连接池
     * @return
     */
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     * 提供Connection
     * @return
     * @throws Exception
     */
    public static Connection getConnection()throws Exception{
        return dataSource.getConnection();
    }

    /**
     * 释放资源
     * @param rs
     * @param st
     * @param conn
     * @throws SQLException
     */
    public static void closeAll(ResultSet rs, Statement st, Connection conn) throws SQLException {
        if(rs!= null){ rs.close(); }
        if(st != null){st.close();}
        if(conn != null){conn.close();}
    }

}
