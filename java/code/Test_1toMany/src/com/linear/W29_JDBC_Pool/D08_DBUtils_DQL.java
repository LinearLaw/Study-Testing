package com.linear.W29_JDBC_Pool;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;


/*
    30.9、使用DBUtils，做DQL操作

 */
public class D08_DBUtils_DQL {

    public QueryRunner qr;

    @Before
    public void beforeTest(){
        qr = new QueryRunner(JdbcUtils.getDataSource());
    }

    // 查询结果是一个值
    @Test
    public void queryOne()throws Exception{
        Object obj = qr.query("select * from emp where id = ?",new ScalarHandler<>(),3);

        System.out.println("\n【Result】" + obj);
    }

    // 查询的结果用map封装
    @Test
    public void queryMap()throws Exception{
        Map<String, Object> m = qr.query("select * from emp where id=?",new MapHandler(),2);
        System.out.println("\n【Result】" + m);
    }

    // 查询结果为多条数据，将其封装成Map
    @Test
    public void queryListMap()throws Exception{
        List<Map<String,Object>> li = qr.query("select * from emp",new MapListHandler());
        System.out.println("\n【Result】" + li);
    }

    // 查询结果单条，使用Bean对象进行封装
    @Test
    public void queryJavaBean()throws Exception{
        Emp e = qr.query("select * from emp where id=?",new BeanHandler<>(Emp.class),2);
        System.out.println("\n【Result】" + e);
    }

    // 查询结果为多条，使用Bean对象封装，放入list容器
    @Test
    public void queryListBean()throws Exception{
        List<Emp> list = qr.query("select * from emp",new BeanListHandler<>(Emp.class));
        for (Emp e:list){
            System.out.println(e + "\n");
        }
    }
}
