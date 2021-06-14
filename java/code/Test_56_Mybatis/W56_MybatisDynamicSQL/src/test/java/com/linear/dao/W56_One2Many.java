package com.linear.dao;

import com.linear.domain.DAccount;
import com.linear.domain.DAccountUser;
import com.linear.domain.DUser;
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
 *          用户和角色
 *          一个用户可以对应多个角色，一个角色可以有多个用户
 *
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
     *  56.8.1 一对多
     *      查询account表，同时将account表对应的user查出来
     *      此时可以看到，Account的user对象也被赋予了相应的值
     */
    @Test
    public void testAccountFindAll(){
        List<DAccount> list = accountDao.findAll();
        for (DAccount li : list){
            System.out.println(li);
        }
    }

    /**
     * 56.8.2 一对多
     *      查询account表，同时将account表对应的user查出来
     *      只取其中的部分内容。
     */
    @Test
    public void testAccountFindAllSimple(){
        List<DAccountUser> list = accountDao.findAllSimple();
        for (DAccountUser au : list){
            System.out.println(au);
        }
    }

    /**
     * 56.8.3 一对多
     *      查询user表，同时将user旗下的所有account作为集合查出来
     */
    @Test
    public void testUserFindAll(){
        List<DUser> lu = userDao.findAll();
        for (DUser li : lu){
            System.out.println(li);
            System.out.println(li.getAlist());
            System.out.println();
        }
    }

}
