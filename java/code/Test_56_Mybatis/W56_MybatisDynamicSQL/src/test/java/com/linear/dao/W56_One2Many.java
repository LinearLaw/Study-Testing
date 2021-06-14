package com.linear.dao;

import com.linear.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * 56.8  Mybatis 多表查询
 *      - 一对多
 *          账户和用户，
 *          用户可以有多个账号，一个账号只有一个所属用户
 *
 *      - 多对多
 *
 */
public class W56_One2Many {
    // mybatis初始化
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private DUserDao userDao;
    private DAccountDao accountDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);

        session = factory.openSession();

        userDao = session.getMapper(DUserDao.class);
        accountDao = session.getMapper(DAccountDao.class);
    }

    @After
    public void destroy() throws Exception{
        session.close();
        in.close();
    }

    /**
     *  56.8.1 查询account表，同时将account表对应的user查出来
     *      此时可以看到，Account的user对象也被赋予了相应的值
     */
    @Test
    public void testAccountFindAll(){
        List<Account> list = accountDao.findAll();
        for (Account li : list){
            System.out.println(li);
        }
    }
}
