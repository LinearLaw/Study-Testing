package com.linear.dao;

import com.linear.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 新增一条记录
     * @param user
     */
    void saveUser(User user);

}
