package linear.dao;

import com.linear.domain.User;

import java.util.List;


/**
 * 56.1、用户持久层接口
 */
public interface IUserDao {

    /**
     * 查找所有用户
     * @return
     */
    List<User> findAll();
}
