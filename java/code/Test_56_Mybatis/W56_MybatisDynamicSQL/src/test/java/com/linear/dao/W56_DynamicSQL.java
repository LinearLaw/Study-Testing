package com.linear.dao;

import com.linear.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *  56.7  测试动态SQL
 *
 */
public class W56_DynamicSQL {

    /**
     * 56.7、初始化mybatis
     */
    private static InputStream in;
    private static SqlSessionFactory factory;
    private static SqlSession session;
    private static CUserDao userDao;

    @BeforeClass
    public static void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);

        session = factory.openSession();

        userDao = session.getMapper(CUserDao.class);
    }
    @AfterClass
    public static void destroy() throws Exception {
        session.commit();

        session.close();
        in.close();
    }

    /**
     * 56.7、获取所有
     */
    @Test
    public void findAll(){
        List<User> uss = userDao.findAll();
        for(User user : uss){
            System.out.println(user);
        }
    }

    // ----------------------------------------------------------

    /** 56.7.1、传入一个user，按user拥有的字段进行查找 */
    @Test
    public void testFindByUser(){
        User user = new User();

        user.setAddress("美国省");
        user.setSex("男");

        System.out.println(user);
        User u = userDao.findByUser(user);
        System.out.println(u);
    }

    /** 56.7.2 传入一个user,按user拥有的字段查找一个列表 */
    @Test
    public void testFindByUser2(){
        User user = new User();
        user.setAddress("%北京%");
        user.setSex("男");

        List<User> list = userDao.findListByUser(user);
        System.out.println(list.size());
        for (User li : list){
            System.out.println(li);
        }
    }

    /** 56.7.3 foreach遍历  */
    @Test
    public void testFindListByList(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(43);
        list.add(46);

        List<User> ul = userDao.findListByIdList(list);
        for (User u : ul){
            System.out.println(u);
        }
    }

}
