package com.linear.study.dao;

import com.linear.study.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @desc 使用注解来配置mybatis
 */
public interface UserAnMapper {

    @Select("select * from user")
    List<User> findAll();
}
