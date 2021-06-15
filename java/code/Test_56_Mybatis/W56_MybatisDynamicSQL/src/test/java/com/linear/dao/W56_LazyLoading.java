package com.linear.dao;

import com.linear.domain.FAccount;
import com.linear.domain.FUser;
import com.linear.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class W56_LazyLoading {
    /**
     * 56.13、延迟加载
     */
    private static InputStream in;
    private static SqlSessionFactory factory;
    private static SqlSession session;
    private static FAccountDao accountDao;
    private static FUserDao userDao;

    @BeforeClass
    public static void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);

        session = factory.openSession();

        accountDao = session.getMapper(FAccountDao.class);
        userDao = session.getMapper(FUserDao.class);
    }
    @AfterClass
    public static void destroy() throws Exception {
        // session.commit();

        session.close();
        in.close();
    }

    /**
     *  56.13、延迟加载 - 一对一查询
     */
    @Test
    public void testFindAccountAll(){
        // 如果只执行findAll，此时就只查了account表
        List<FAccount> list = accountDao.findAll();

        // 如果此时对list遍历，去访问其元素成员，此时就会接连将user表也查出来。
        for (FAccount fa : list){
            System.out.println(fa);
        }
    }

    /**
     * 56.13 延迟加载 - 一对多查询
     */
    @Test
    public void testFindUserAll(){
        /**
         * 没有延迟加载时：先查user表，再查account表
         * 有延迟加载后，此时只查了user表
         */
        List<FUser> user = userDao.findAll();

        // 当访问list中的元素时，会触发account表的查找
        for (FUser fs : user){
            System.out.println(fs);
        }
    }



}
