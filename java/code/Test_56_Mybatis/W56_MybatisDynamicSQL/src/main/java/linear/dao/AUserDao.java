package linear.dao;

import com.linear.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 56.5、使用注解实现mybatis查找所有
 *      此时，不需要再去创建resources的xml，
 *      只需要在主配置文件中指定当前的DAO即可
 */
public interface AUserDao {

    @Select("select * from user")
    List<User> findAll();
}
