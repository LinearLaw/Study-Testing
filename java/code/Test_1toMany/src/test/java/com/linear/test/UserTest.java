package com.linear.test;

import com.linear.dao.IUserDao;
import com.linear.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init()throws Exception{
        // 1、加载配置
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2、根据配置，获取一个工厂对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        // 3、利用工厂对象，生成一个session
        sqlSession = factory.openSession();

        // 4、利用session，获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy()throws Exception{
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();

        for (User user:users){
            System.out.println(user);
        }
    }

}
