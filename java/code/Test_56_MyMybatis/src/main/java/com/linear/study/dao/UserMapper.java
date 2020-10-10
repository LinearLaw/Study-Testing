package com.linear.study.dao;

import com.linear.study.domain.User;

import java.util.List;

/**
 * @desc 使用xml来配置mybatis
 */
public interface UserMapper {

    List<User> findAll();
}
