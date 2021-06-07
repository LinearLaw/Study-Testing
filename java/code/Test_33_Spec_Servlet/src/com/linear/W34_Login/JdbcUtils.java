package com.linear.W34_Login;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
    JDBC工具类
    使用C3P0作为连接池
 */
public class JdbcUtils {


    private static ComboPooledDataSource dataSource;
    static{
        dataSource = new ComboPooledDataSource();
    }

    public static DataSource getDataSource(){return dataSource;}

    public static Connection getConnection()throws Exception{
        return dataSource.getConnection();
    }

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
