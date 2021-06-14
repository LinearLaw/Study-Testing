package com.linear.dao;

import com.linear.domain.User;

import java.util.List;

/**
    56.7、动态SQL
        核心其实在于xml配置里面的东西

 */
public interface CUserDao {

    /**
     * 56.6.1、获取所有
     * @return
     */
    List<User> findAll();

    /**
     * 56.6.5、根据id查找
     */
    User findById(int id);

    /**
     * 56.6.6、模糊查询
     */
    List<User> findByName(String username);

    // -----------------------------------

    /** 56.7.1、条件查询 - 1 */
    User findByUser(User user);

    /** 56.7.2 条件查询 - 2  */
    List<User> findListByUser(User user);

    /** 56.7.3 [select * form user where id in(46,47,48)] */
    List<User> findListByIdList(List<Integer> list);
}
