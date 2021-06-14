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

    @Test
    public void testUserFindAllWithLazy(){

    }

}
