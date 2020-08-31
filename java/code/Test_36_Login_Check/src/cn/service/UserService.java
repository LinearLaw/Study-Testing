package cn.service;

import cn.domain.User;

import java.util.List;

public interface UserService {

    /* 登录 */
    User login(User user);

    public List<User> findAll();

    void addUser(User user);

    void deleteUser(User user);

    void findUserById(String id);

    void updateUser(User user);



}
