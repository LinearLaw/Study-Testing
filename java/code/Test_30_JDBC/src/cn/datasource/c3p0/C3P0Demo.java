package cn.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Demo {
    public static void main(String[] args) throws SQLException{
        /* 1、获取DataSource */
        DataSource ds = new ComboPooledDataSource();

        /* 2、获取所有连接 */
        for (int i=1;i<=10;i++){
            Connection conn = ds.getConnection();
            System.out.println(i + " : " + conn);

            conn.close();
        }
    }
}
