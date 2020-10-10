package com.linear.study.test;


import com.linear.study.dao.UserAnMapper;
import com.linear.study.dao.UserMapper;
import com.linear.study.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    /**
     * How to use mybatis for sql query
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{

        TestAnnotation();
    }

    public static void TestXml()throws Exception{
        // 1、读取mybatis主配置
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2、使用SqlSessionFactoryBuilder，创建一个builder，然后用builder创建一个工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        // 3、使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();

        // xml方式，引入UserMapper
        // 4、利用SqlSession对象，创建UserMapper的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);

        // 5、可以直接用这个代理对象，去调用dao的方法
        List<User> users = userMapper.findAll();

        // 6、将获取到的数据打印出来
        for(User user:users){
            System.out.println(user);
        }

        // 7、执行完毕，关闭连接
        session.close();
        in.close();
    }

    public static void TestAnnotation()throws Exception{
        // 1、读取mybatis主配置
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2、使用SqlSessionFactoryBuilder，创建一个builder，然后用builder创建一个工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        // 3、使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();

        // 注解方式，使用UserAnMapper
        // 4、利用SqlSession对象，创建UserMapper的代理对象
        UserAnMapper userMapper = session.getMapper(UserAnMapper.class);

        // 5、可以直接用这个代理对象，去调用dao的方法
        List<User> users = userMapper.findAll();

        // 6、将获取到的数据打印出来
        for(User user:users){
            System.out.println(user);
        }

        // 7、执行完毕，关闭连接
        session.close();
        in.close();
    }
}
