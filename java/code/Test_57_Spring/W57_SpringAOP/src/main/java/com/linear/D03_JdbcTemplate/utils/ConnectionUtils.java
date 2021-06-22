package com.linear.D03_JdbcTemplate.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/***
 * 57.4、事务控制，连接的工具类
 *      将当前的线程和当前的Connection进行绑定；
 * 		每次需要连接时，从这里取，
 *  			如果当前线程没有连接绑定，则去连接池取，并将其和当前线程绑定
 *  			如果当前线程有连接绑定，则使用当前线程绑定的连接，从而保持连接一致性。
 */
public class ConnectionUtils {

    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    private DataSource dataSource;
    public void setDataSource(DataSource dataSource){this.dataSource=dataSource;}

    /** 将连接和线程进行解绑 */
    public void removeConnection(){
        tl.remove();
    }
    /***
     * 获取连接
     * @return
     */
    public Connection getThreadConnection(){
        try {
            // 1、从ThreadLocal上获取一个连接
            Connection conn = tl.get();

            /* 2、判断当前线程是否已经存在连接，
            *       存在 - 使用现有的连接
            *       不存在 - 将当前线程和连接绑定
            * */
            if(conn == null){
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            // 3、返回连接
            return conn;
        } catch (SQLException e) { throw new RuntimeException(); }
    }

}
