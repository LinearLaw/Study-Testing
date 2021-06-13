package com.linear.dao;

import com.linear.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class CUserDaoTest {

    /**
     * 56.6、初始化mybatis
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
     * 56.6.1、获取所有
     */
    @Test
    public void findAll(){
        List<User> uss = userDao.findAll();
        for(User user : uss){
            System.out.println(user);
        }
    }

    /**
     * 56.6.2、插入一条数据
     */
    @Test
    public void saveUser(){
        User user = new User();
        user.setAddress("美国省");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setUsername("Trump");

        System.out.println("before: " + user);
        userDao.saveUser(user);
        System.out.println("after: " + user);
    }

    /**
     * 56.6.3、更新数据
     */
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(50);
        user.setUsername("Abama");
        user.setSex("女");
        user.setAddress("印度省");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }

    /**
     * 56.6.4、删除数据
     */
    @Test
    public void deleteById(){
        userDao.deleteById(48);
    }

    /**
     * 56.6.5、根据id查找
     */
    @Test
    public void findById(){
        User user = userDao.findById(50);
    }

    /**
     * 56.6.6、根据username模糊查询
     */
    @Test
    public void findByName(){
        List<User> list = userDao.findByName("%Trump%");
        for(User user : list){
            System.out.println(user);
        }
    }

    /**
     * 56.6.7、获取总用户数
     */
    @Test
    public void findTotal(){
        int count = userDao.findTotal();
        System.out.println(count);
    }
}
