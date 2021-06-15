package com.linear.dao;

import com.linear.domain.DAccount;
import com.linear.domain.DUser;
import org.junit.Test;

import java.util.List;

/***
 * 56.14 注解完成复杂关系映射
 *
 */
public class W56_ManyMapper extends W56_AnnotationCRUD {

    /** 1、查找Account表，同时把Account关联的user信息也查出来 */
    @Test
    public void testAccountFindAll(){
        List<DAccount> list = accountDao.findAccountAll();

        for (DAccount da : list){
            System.out.println(da);
        }
    }

    /** 2、查找user表，同时把user关联的所有account查出来  */
    @Test
    public void testUserFindAll(){
        List<DUser> list = userDao.findUserAll();
        for (DUser da : list){
            System.out.println(da);
            System.out.println(da.getAlist());
        }
    }

}
