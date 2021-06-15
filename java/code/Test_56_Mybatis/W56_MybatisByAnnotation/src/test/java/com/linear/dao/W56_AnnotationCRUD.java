package com.linear.dao;

import com.linear.domain.DAccount;
import com.linear.domain.DUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


/***
 * 56.14 注解完成CRUD操作
 *
 */
public class W56_AnnotationCRUD {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    public DUserDao userDao;
    public DAccountDao accountDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();

        userDao = session.getMapper(DUserDao.class);
        accountDao = session.getMapper(DAccountDao.class);
    }

    @After
    public void destory()throws Exception{
        session.commit();
        session.close();
        in.close();
    }

    /** 1、查询所有用户 */
    @Test
    public void findAll() {
        List<DUser> list = userDao.findAll();

        for (DUser us : list){
            System.out.println(us);
        }
    }

    /** 2、根据id查询用户  */
    @Test
    public void findById(){
        DUser user = userDao.findById(50);
        System.out.println(user);
    }

    /** 3、保存用户信息 */
    @Test
    public void saveUser(){
        DUser user = new DUser();
        user.setUserName("安倍");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("日本市");

        int res = userDao.saveUser(user);
        System.out.println(res);
    }

    /** 4、更新用户信息 */
    @Test
    public void updateUser(){
        // 先查出指定id的用户
        DUser user = userDao.findById(50);

        // 然后更新这个用户的信息
        user.setUserName("马化云");
        int res = userDao.updateById(user);
    }

    /** 5、删除用户 */
    @Test
    public void deleteById(){
        int res = userDao.deleteById(53);
        System.out.println(res);
    }

    /** 6、使用聚合函数 */
    @Test
    public void findTotal(){
        int total = userDao.findTotal();
        System.out.println(total);
    }

    /** 7、模糊查询 */
    @Test
    public void findByName(){
        List<DUser> list = userDao.findByName("%马%");
        for (DUser us : list){
            System.out.println(us);
        }
    }
}