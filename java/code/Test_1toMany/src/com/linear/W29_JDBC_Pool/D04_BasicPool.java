package com.linear.W29_JDBC_Pool;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/*
    30.1、数据库连接池
    三个要点
        - 连接池容器
        - 获取连接
        - 归还连接

    主要是实现DateSource接口
 */
public class D04_BasicPool {

    public static void main(String[] args){

    }

}

/*
    自定义数据库连接池类 - 简单版
 */
class MyDataSource implements DataSource {

    private LinkedList<Connection> pool = new LinkedList<>();
    private int connCount = 5;

    // 1、初始化，创建多个连接，放入pool中
    public MyDataSource()throws Exception{
        for(int i = 0; i < connCount;i++){
            Connection conn = D01_JDBCUtils.getConnection();
            pool.add(conn);
        }
    }

    // 2、申请一个连接
    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = pool.removeFirst(); // 从链表头中，弹出一个Connection
        return conn;
    }

    // 3、归还连接
    public void recycle(Connection conn){
        pool.addFirst(conn);
    }

    // 下面的重写方法都采用默认值
    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
