package com.linear.test;

import com.linear.dao.IUserDao;
import com.linear.domain.QueryVo;
import com.linear.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init()throws Exception{
        // 1、读取xml配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2、获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        // 3、获取SqlSession对象
        sqlSession = factory.openSession();

        // 4、获取dao代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy()throws Exception{
        sqlSession.commit();

        sqlSession.close();
        in.close();
    }

    // 1、查询所有
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User u:users){
            System.out.println(u);
        }
    }

    // 2、插入一条数据
    @Test
    public void testInsertOne(){
        User user = new User();
        user.setUserName("马化云");
        user.setUserAddress("美国");
        user.setUserBirthday(new Date());
        user.setUserSex("男");

        System.out.println("在save之前："+user);
        userDao.saveUser(user);
        System.out.println("在save之后："+user);
    }

    // 3、修改一条
    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserId(49);
        user.setUserName("马化龙");
        user.setUserAddress("美国");
        user.setUserBirthday(new Date());
        user.setUserSex("男");

        userDao.updateUser(user);

    }

    // 4、根据id删除一条
    @Test
    public void testDeleteById(){
        userDao.deleteUserById(49);
    }

    /* 5、根据id查找用户
     *    这里就出现了一个问题，User类和数据库的user表的字段不一致，
     *    导致查找出来的用户是null。
     *
     *    两种方法：
     *      一、select的时候起别名，
     *      二、ResultMap标签进行配置
     */
    @Test
    public void testFindById(){
        User u = userDao.findById(48);
        System.out.println("Result--------------------"+u);
    }

    /**
     * 6、模糊查询
     */
    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%王%");
        for (User u:users){
            System.out.println(u);
        }
    }

    /**
     * 7、查询总用户数
     */
    @Test
    public void testFindTotal(){
        int total = userDao.findTotal();
        System.out.println("Total------------"+total);
    };

    /**
     * 8、多个类的参与的查询
     */
    @Test
    public void testFindUserByVo(){
        User user = new User();
        user.setUserName("%王%");

        QueryVo vo = new QueryVo();
        vo.setUser(user);

        List<User> users = userDao.findUserByVo(vo);
        for (User u: users){
            System.out.println(u);
        }
    }

}
