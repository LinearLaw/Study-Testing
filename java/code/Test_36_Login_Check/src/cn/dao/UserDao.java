package cn.dao;

import cn.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @desc 用户操作的DAO
 */
public interface UserDao {

    public List<User> findAll();
    User findUserByUsernameAndPassword(String username,String password);

    void add(User user);
    void delete(int id);

    void update(User user);

    User findById(int i);
    int findTotalCount(Map<String,String[]> condition);

    List<User> findByPage(int start, int rows, Map<String,String[]> condition);
}
