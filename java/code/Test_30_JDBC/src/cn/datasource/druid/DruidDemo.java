package cn.datasource.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo {

    public static void main(String[] args)throws Exception {

        /* 1、指定配置文件，加载 */
        Properties pro = new Properties();
        InputStream is = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(is);

        /* 2、根据配置文件，创建数据库连接池 */
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);

        /* 3、获取一个连接对象 */
        Connection conn = ds.getConnection();
        System.out.println("connection::::::" + conn);

    }
}
