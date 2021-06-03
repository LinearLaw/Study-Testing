package com.linear.W29_JDBC_Pool;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Before;
import org.junit.Test;

/*
    30.8、DBUtils，是Apache组织的一个堆JDBC进行了简单封装的开源工具库。
        主要目的是简化JDBC应用程序的开发。

        这里我们重写了JdbcUtils，改为使用C3P0管理连接池。

        DBUtils执行DML操作
 */
public class D07_DBUtils_DML {

    // QueryRunner，提供了执行SQL语句的常用API
    public QueryRunner qr;

    @Before
    public void beforeTest(){
        qr = new QueryRunner(JdbcUtils.getDataSource());
    }

    @Test
    public void updateTest()throws Exception{
        int i = qr.update("update emp set salary=? where id = ?",8000,2);
        System.out.println(i);
    }
    @Test
    public void deleteTest()throws Exception{
        int i = qr.update("delete from emp where id = 3");
        System.out.println(i);
    }
    @Test
    public void insertTest()throws Exception{
        int i = qr.update(
                "insert into emp values(?,?,?,?,?,?)",
                3,"美国","男",9999,null,null

        );
        System.out.println(i);
    }
}
