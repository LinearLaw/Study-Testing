package com.linear.study.dao;

import com.linear.study.domain.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll();
}
