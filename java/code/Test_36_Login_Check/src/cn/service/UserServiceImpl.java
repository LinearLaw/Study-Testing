package cn.service;

import cn.dao.UserDao;
import cn.dao.UserDaoImpl;
import cn.domain.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    /**
     * 登录service，调用dao，将查找的结果返回
     */
    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(
                user.getUsername(),
                user.getPassword()
        );
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void findUserById(String id) {

    }

    @Override
    public void updateUser(User user) {

    }
}
