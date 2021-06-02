package com.linear.W29_JDBC_Pool;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/*
    29.1、JDBC Utils 工具类
        - 加载配置文件
        - 初始化连接池对象
        - 提供方法
            - 提供数据库连接池
            - 获取连接池
            - 释放资源

 */
public class D01_JDBCUtils {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    // 读取资源文件，只需要加载一次即可，所以使用static代码块
    static{
        try {
            // 1、创建Properties集合类，用来读取配置文件
            Properties pro = new Properties();

            // 2、类加载器，用来获取配置文件的路径
            ClassLoader clsLoader = D01_JDBCUtils.class.getClassLoader();
            URL res = clsLoader.getResource("jdbc.properties");
            String path = res.getPath();

            // 3、加载读取配置文件
            pro.load(new FileReader(path));

            // 4、将配置文件的内容读取到变量中。
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");

            // 5、加载驱动包
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }

    /**
     * 释放连接资源
     * @param stmt
     * @param conn
     */
    public static void close(Statement stmt,Connection conn){
        if(stmt != null){
            try {
                stmt.close();
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
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(stmt,conn);
    }
}
