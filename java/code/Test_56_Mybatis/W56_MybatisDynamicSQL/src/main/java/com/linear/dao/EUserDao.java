package com.linear.dao;

import com.linear.domain.EUser;

import java.util.List;

/**
 * 56.10 多表查询
 *      - 多对多
 *      一个用户表，一个角色表
 *      一个用户可以对应多个角色，一个角色可以有多个用户
 *
 *      多对多关系，需要有中间表
 *
 */
public interface EUserDao {
    List<EUser> findAll();
}
