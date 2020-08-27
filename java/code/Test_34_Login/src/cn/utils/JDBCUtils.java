package cn.utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    private static DataSource ds;

    /* 1、加载JDBC Druid配置 */
    static{
        try {
            Properties pro = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);

            try {
                ds = DruidDataSourceFactory.createDataSource(pro);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch(NoClassDefFoundError e){
            e.printStackTrace();
        }
    }

    /* 2、获取datasource对象 */
    public static DataSource getDataSource(){
        return ds;
    }

    /* 3、获取connection对象 */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
