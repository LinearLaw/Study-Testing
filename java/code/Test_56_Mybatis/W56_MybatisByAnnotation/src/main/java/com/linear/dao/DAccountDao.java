package com.linear.dao;

import com.linear.domain.DAccount;
import com.linear.domain.DUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 56.14  注解的复杂关系映射 - 一对一
 *
 */
public interface DAccountDao {

    /** 0、传入一个uid，将与该uid关联的所有account查出来 */
    @Select("select * from account where uid=#{uid}")
    @ResultMap("accountMap")
    List<DAccount> findByUid(Integer uid);

    /**
     * 1、查找Account表，同时把Account关联的user信息也查出来
     */
    @Select("select * from account")
    @Results(id="accountMap",
        value={
                @Result(id=true,property = "id",column = "id"),
                @Result(property = "uid",column = "uid"),
                @Result(property = "money",column = "money"),
                @Result(property = "user",column = "uid",
                    one=@One(select="com.linear.dao.DUserDao.findById",
                        fetchType = FetchType.LAZY   // 开启延迟加载
                    )
                )
        }
    )
    List<DAccount> findAccountAll();


}
