package com.linear.dao;


import com.linear.domain.FUser;

import java.util.List;


/**
 * 56.13 延迟加载 - 用户表
 */
public interface FUserDao {
    FUser findById(Integer userId);

    List<FUser> findAll();
}
