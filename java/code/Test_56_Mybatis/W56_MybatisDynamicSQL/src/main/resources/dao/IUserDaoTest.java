package com.linear.dao;


import com.linear.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/*
    测试
 */
public class IUserDaoTest {

    public static void main(String[] args) throws Exception{
        init();

        useXML();
        System.out.println("-------------");
        useAnno();
    }
    private static InputStream in;
    private static SqlSessionFactory factory;
    private static SqlSession session;

    public static void init() throws Exception{
        // 1、读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2、创建SqlSession工厂 - 构建者模式
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);

        // 3、利用工厂创建SqlSession - 工厂模式
        session = factory.openSession();

    }
    public static void destroy() throws Exception{
        // 6、关闭资源
        session.close();
        in.close();
    }
    /**
     * 56.4、使用xml配置，实现mybatis的查找
     * @throws Exception
     */
    public static void useXML() throws Exception{
        // 4、使用SqlSession创建IUserDao的代理对象 - 代理模式
        IUserDao userDao = session.getMapper(IUserDao.class);

        // 5、使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user:users){
            System.out.println(user);
        }
    }

    /**
     * 56.5、注解方式使用mybatis查找
     */
    public static void useAnno() throws Exception{
        AUserDao userDao = session.getMapper(AUserDao.class);

        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }
}
