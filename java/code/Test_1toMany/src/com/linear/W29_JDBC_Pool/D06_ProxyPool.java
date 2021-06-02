package com.linear.W29_JDBC_Pool;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/*
    30.3、使用动态代理，增强Connection的close方法
        Proxy.newProxyInstance()


 */
public class D06_ProxyPool {

    public static void main(String[] args){

    }

}

class MyProxyDataSource implements DataSource {

    private LinkedList<Connection> pool = new LinkedList<>();
    public MyProxyDataSource() throws Exception {
        for (int i = 0;i < 5;i++){
            Connection conn = D01_JDBCUtils.getConnection();

            // 创建Connection，进行动态代理
            Connection proxyConn = (Connection) Proxy.newProxyInstance(
                    conn.getClass().getClassLoader(),
                    new Class[]{Connection.class},

                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            String name = method.getName();
                            if("close".equals(name)){
                                pool.addLast((Connection) proxy);
                                return null;
                            }

                            return method.invoke(conn,args);
                        }
                    }
            );
            pool.add(proxyConn);
        }
    }

    // 获取连接
    @Override
    public Connection getConnection()throws SQLException {
        Connection conn = pool.removeFirst();
        return conn;
    }

    // 下面都是Override的方法，采用默认值
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
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
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


