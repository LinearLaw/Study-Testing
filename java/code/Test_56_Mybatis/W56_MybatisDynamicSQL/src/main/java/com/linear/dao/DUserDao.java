package com.linear.dao;

import com.linear.domain.DUser;

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
public interface DUserDao {


    /**
     * 56.6.1、获取所有
     * @return
     */
    List<DUser> findAll();

}
