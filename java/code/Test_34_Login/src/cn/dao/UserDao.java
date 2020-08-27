package cn.dao;

import cn.domain.User;
import cn.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/* DAO，用来写数据库的相关操作 */
public class UserDao {

    private JdbcTemplate tpl = new JdbcTemplate(JDBCUtils.getDataSource());

    public User login(User loginUser){

        try {
            String sql = "select * from user where username = ? and password = ?";

            /**
             * 参数一，sql语句
             * 参数二，不知道
             * 参数三、四，sql中占位符的值
             */
            User user = tpl.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(),
                    loginUser.getPassword()
            );
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
}
