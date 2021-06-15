package com.linear.dao;

import com.linear.domain.ERole;
import com.linear.domain.EUser;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class W56_Many2Many {

    // mybatis初始化
    private InputStream inp;
    private SqlSessionFactory fac;
    private SqlSession sqlSession;
    private ERoleDao roleDao;
    private EUserDao userDao;

    @Before
    public void init() throws Exception{
        inp = Resources.getResourceAsStream("SqlMapConfig.xml");
        fac = new SqlSessionFactoryBuilder().build(inp);
        sqlSession = fac.openSession();

        roleDao = sqlSession.getMapper(ERoleDao.class);
        userDao = sqlSession.getMapper(EUserDao.class);
    }

    @After
    public void destroy() throws Exception{
        sqlSession.close();
        inp.close();
    }

    /**
     * 56.10、 多对多，
     *      查找role，将与role关联的user也查出来
     */
    @Test
    public void testRoleFindAll(){
        List<ERole> list = roleDao.findAll();
        for(ERole er : list){
            System.out.println(er);
            System.out.println();
        }
    }


    /**
     * 56.11、多对多
     *      查找user，将与user关联的role也查出来
     */
    @Test
    public void testUserFindAll(){
        List<EUser> list = userDao.findAll();
        for (EUser us : list){
            System.out.println(us);
        }
    }

    /**
     * 56.12 一级缓存默认开启，
     *      查询两次同样的内容，实际只查了一次数据库
     */
    @Test
    public void testUserFindAll_1Level(){
        List<EUser> list1 = userDao.findAll();
        for (EUser us : list1){
            System.out.println(us);
        }
        List<EUser> list2 = userDao.findAll();

        // 因为有一级缓存，所以这里是true
        System.out.println(list1 == list2);
    }

    /**
     * 56.12 二级缓存
     *      先配置主配置文件 SqlMapConfig.xml
     *      再配置mapper，增加一个 <cache></cache>
     *
     */
    @Test
    public void testUserFindAll_2Level(){
        List<EUser> list1 = userDao.findAll();
        for (EUser us : list1){
            System.out.println(us);
        }

        sqlSession.close();

        SqlSession sa = fac.openSession();
        EUserDao d = sa.getMapper(EUserDao.class);

        List<EUser> list2 = d.findAll();

        /* 效果：两次调起查询，结果只出现一次数据库查询，
                因为close，一级缓存失效，所以这里作用到了二级缓存。
                但是list1 == list2 返回了false
        */
        System.out.println(list1 == list2);

    }
}
