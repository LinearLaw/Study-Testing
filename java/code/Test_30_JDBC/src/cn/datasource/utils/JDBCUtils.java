package cn.datasource.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @desc 连接池工具类
 *
 * 1、获取一个数据库连接
 * 2、释放资源
 * 3、获取连接池对象
 *
 */
public class JDBCUtils {
    /* 1、DataSource 连接对象 */
    private static DataSource ds;

    /* 静态代码块里面加载Druid的配置文件，并给 DataSource 赋值 */
    static{
        try{
            /* 2、加载配置文件 */
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));

            /* 3、获取连接池对象 */
            ds = DruidDataSourceFactory.createDataSource(pro);
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /* 从连接池中，获取一个连接 */
    public static Connection getConnection()throws SQLException {
        return ds.getConnection();
    }

    /* 关闭连接。释放资源
     * 这里用了多态。
     */
    public static void close(Statement stmt, Connection conn){
        close(null,stmt,conn);
    }
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs != null){
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        if(stmt != null){
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    /* 获取连接池对象 */
    public static DataSource getDataSource(){return ds;}
}
