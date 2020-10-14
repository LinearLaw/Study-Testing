package com.linear.dao;

import com.linear.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查找所有用户，同时获取到用户旗下的所有account信息
     * @return
     */
    List<User> findAll();
}
