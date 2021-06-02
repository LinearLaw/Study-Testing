package com.linear.W29_JDBC_Pool;

import sun.awt.image.ImageWatched;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

/*
    30.2、数据库连接池 -
    自定义装饰设计模式版

    在简单版中，我们定义了 recycle() 用来归还连接，
        但是，为什么不直接用自带的 close() 方法来归还呢？

    第一种思路：装饰设计模式！对 close() 方法进行增强
        设置一个装饰类，对Connection进行装饰，重写其 close() 方法
        连接池中，使用这个装饰后的Connection作为元素。

    第二种思路：动态代理！对 close() 方法进行增强


 */
public class D05_DecPool {


}

// 自定义连接池
class MyWrapperDataSource implements DataSource{
    private LinkedList<Connection> pool = new LinkedList<>();

    public MyWrapperDataSource()throws SQLException{
        for (int i = 0; i< 5;i++){
            Connection conn = D01_JDBCUtils.getConnection();

            // 将原有的connection对象进行装饰
            WrapperConnection wc = new WrapperConnection(conn,pool);
            pool.add(wc);
        }
    }

    @Override
    public Connection getConnection()throws SQLException{
        Connection conn = pool.removeFirst();
        return conn;
    }

    @Override
    public int hashCode() {return super.hashCode(); }
    @Override
    public boolean equals(Object obj) { return super.equals(obj); }
    @Override
    protected Object clone() throws CloneNotSupportedException { return super.clone(); }
    @Override
    public String toString() { return super.toString(); }
    @Override
    protected void finalize() throws Throwable { super.finalize(); }
    @Override
    public Connection getConnection(String username, String password) throws SQLException { return null; }
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException { return null; }
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException { return false; }
    @Override
    public PrintWriter getLogWriter() throws SQLException { return null; }
    @Override
    public void setLogWriter(PrintWriter out) throws SQLException { }
    @Override
    public void setLoginTimeout(int seconds) throws SQLException { }
    @Override
    public int getLoginTimeout() throws SQLException { return 0; }
    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException { return null; }
}

// 包装Connection
class WrapperConnection implements Connection {
    // 被装饰的Connection
    private Connection conn;

    private LinkedList<Connection> pool;

    public WrapperConnection(Connection conn, LinkedList<Connection> p){
        this.conn = conn;
        this.pool = p;
    }

    // 曾倩的close方法
    @Override
    public void close() throws SQLException{
        pool.addLast(this);
    }

    // 下面的都是实现的类，使用默认操作。
    public WrapperConnection() { super(); }
    @Override
    public int hashCode() { return super.hashCode(); }

    @Override
    public boolean equals(Object obj) { return super.equals(obj); }

    @Override
    protected Object clone() throws CloneNotSupportedException { return super.clone(); }

    @Override
    public String toString() { return super.toString(); }

    @Override
    protected void finalize() throws Throwable { super.finalize(); }

    @Override
    public Statement createStatement() throws SQLException { return null; }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException { return null; }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException { return null; }

    @Override
    public String nativeSQL(String sql) throws SQLException { return null; }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException { }

    @Override
    public boolean getAutoCommit() throws SQLException { return false; }

    @Override
    public void commit() throws SQLException { }

    @Override
    public void rollback() throws SQLException { }

    @Override
    public boolean isClosed() throws SQLException { return false; }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException { return null; }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException { }

    @Override
    public boolean isReadOnly() throws SQLException { return false; }

    @Override
    public void setCatalog(String catalog) throws SQLException { }

    @Override
    public String getCatalog() throws SQLException { return null; }

    @Override
    public void setTransactionIsolation(int level) throws SQLException { }

    @Override
    public int getTransactionIsolation() throws SQLException { return 0; }

    @Override
    public SQLWarning getWarnings() throws SQLException { return null; }

    @Override
    public void clearWarnings() throws SQLException { }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException { return null; }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException { return null; }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException { return null; }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException { return null; }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException { }

    @Override
    public void setHoldability(int holdability) throws SQLException { }

    @Override
    public int getHoldability() throws SQLException {
        return 0;
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return null;
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return null;
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {

    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {

    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return null;
    }

    @Override
    public Clob createClob() throws SQLException {
        return null;
    }

    @Override
    public Blob createBlob() throws SQLException {
        return null;
    }

    @Override
    public NClob createNClob() throws SQLException {
        return null;
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return null;
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return false;
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {

    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {

    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return null;
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return null;
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return null;
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return null;
    }

    @Override
    public void setSchema(String schema) throws SQLException {

    }

    @Override
    public String getSchema() throws SQLException {
        return null;
    }

    @Override
    public void abort(Executor executor) throws SQLException {

    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return 0;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
