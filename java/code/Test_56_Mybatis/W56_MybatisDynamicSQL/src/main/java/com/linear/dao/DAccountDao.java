package com.linear.dao;

import com.linear.domain.DAccount;
import com.linear.domain.DAccountUser;

import java.util.List;

/**
 * 56.8 多表查询
 *      - 一对多
 *
 *  账户和用户，用户可以有多个账号，一个账号关联一个用户
 *  要求：
 *      查询用户时，可以同时得到用户所包含的账户集合 - 一对多
 *      查询账户时，可以获得账户所属的用户信息 - 一对一
 *
 */
public interface DAccountDao {

    /**
     * 查找Account表，同时把Account关联的user信息也查出来
     */
    List<DAccount> findAll();


    /**
     * 查找Account表，同时把Account关联的user信息也查出来
     * 查出来之后，我们只需要其中部分的信息即可。
     * 所以使用一个类作为输出对象
     */
    List<DAccountUser> findAllSimple();
}
