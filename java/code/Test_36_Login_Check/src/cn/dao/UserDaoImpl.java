package cn.dao;

import cn.domain.User;
import cn.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";

            /* 这里的 BeanPropertyRowMapper 用于对User对象的自动封装 */
            User use = template.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    username,
                    password
            );
            return use;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User findById(int i) {
        return null;
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        return 0;
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        return null;
    }
}
