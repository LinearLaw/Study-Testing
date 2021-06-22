package com.linear.D03_JdbcTemplate.utils;

import java.sql.SQLException;

/***
 * 57.4、事务控制，事务控制的工具类
 *      用来控制事务 开启、提交、回滚、释放
 */
public class TransactionManager {
    /** 连接工具类 */
    private ConnectionUtils connectionUtils;
    public ConnectionUtils getConnectionUtils() { return connectionUtils; }
    public void setConnectionUtils(ConnectionUtils connectionUtils) { this.connectionUtils = connectionUtils; }

    /** 开启事务控制 */
    public void beginTransaction(){
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) { e.printStackTrace(); }
    }
    /** 提交事务 */
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    /** 回滚事务 */
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    /** 释放连接 */
    public void release(){
        try {
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
