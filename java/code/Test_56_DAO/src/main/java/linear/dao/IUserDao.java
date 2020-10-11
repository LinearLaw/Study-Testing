package linear.dao;

import com.linear.domain.QueryVo;
import com.linear.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 1、查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 2、新增一条记录
     * @param user
     */
    void saveUser(User user);

    /**
     * 3、修改一条记录
     * @param user
     */
    void updateUser(User user);

    /**
     * 4、根据id删除用户
     * @param userId
     */
    void deleteUserById(Integer userId);

    /**
     * 5、根据id查找用户
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 6、利用username进行模糊查询
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 7、获取总用户数
     * @return
     */
    int findTotal();

    /**
     * 8、将User封装到QueryVo类中，便于多类联合查询
     */
    List<User> findUserByVo(QueryVo vo);
}
