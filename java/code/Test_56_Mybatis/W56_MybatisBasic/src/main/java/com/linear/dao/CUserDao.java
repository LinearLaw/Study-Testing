package com.linear.dao;

import com.linear.domain.User;

import java.util.List;

/*
    56.6、，mybatis实现CRUD

 */
public interface CUserDao {

    /**
     * 56.6.1、获取所有
     * @return
     */
    List<User> findAll();


    /**
     * 56.6.2、插入一条数据
     */
    void saveUser(User user);

    /**
     * 56.6.3、更改一条数据
     */
    void updateUser(User user);

    /**
     * 56.6.4、删除一条数据
     */
    void deleteById(int id);


    /**
     * 56.6.5、根据id查找
     */
    User findById(int id);

    /**
     * 56.6.6、模糊查询
     */
    List<User> findByName(String username);

    /**
     * 56.6.7、总用户数
     */
    int findTotal();

}
