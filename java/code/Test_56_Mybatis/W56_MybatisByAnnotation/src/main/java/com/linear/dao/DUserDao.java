package com.linear.dao;

import com.linear.domain.DUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 56.14 基于注解，进行mybatis配置
 *
 */
public interface DUserDao {


    /** 1、查询所有用户 */
    @Select("select * from user")
    @Results(           // 设置结果的映射
            id="userMap",
            value={
                    @Result(id=true, property="uid", column="id"),
                    @Result(property = "userName",column = "username"),
                    @Result(property = "birthday",column = "birthday"),
                    @Result(property = "sex",column = "sex"),
                    @Result(property = "address",column = "address")
            }
    )
    List<DUser> findAll();

    /** 2、根据id查询用户 */
    @Select("select * from user where id=#{id}")
    @ResultMap("userMap")
    DUser findById(Integer id);


    /** 3、保存用户 */
    @Insert("insert into " +
                "user(username, sex, birthday, address) " +
            "values(" +
                "#{userName}, #{sex}, #{birthday}, #{address}" +
            ")")
    @SelectKey(keyColumn = "id", keyProperty = "uid", resultType = Integer.class,
        before=false, statement = "select last_insert_id()"
    )   // 设置前置查询，uid 映射到 id值
    int saveUser(DUser user);


    /** 4、更新用户信息 */
    @Update("update user " +
            "set username=#{userName},sex=#{sex}, " +
            "birthday=#{birthday},address=#{address} " +
            "where id=#{uid}")
    int updateById(DUser user);


    /** 5、删除用户信息 */
    @Delete("delete from user where id=#{id}")
    int deleteById(Integer id);


    /** 6、使用聚合函数 */
    @Select("select count(*) from user")
    int findTotal();

    /** 7、模糊查询 */
    @Select("select * from user where username like #{name}")
    List<DUser> findByName(String name);


    /*****  复杂关系映射  *****/
    /***
     * 2、查找user表，将user关联的账户也全都查出来
     */
    @Select("select * from user")
    @Results(           // 设置结果的映射
            id="userMap2",
            value={
                    @Result(id=true, property="uid", column="id"),
                    @Result(property = "userName",column = "username"),
                    @Result(property = "birthday",column = "birthday"),
                    @Result(property = "sex",column = "sex"),
                    @Result(property = "address",column = "address"),
                    @Result(property = "alist",column = "id",
                            many=@Many(
                                    select = "com.linear.dao.DAccountDao.findByUid",
                                    fetchType = FetchType.LAZY // 开启懒加载
                            )
                    )
            }
    )
    List<DUser> findUserAll();
}
