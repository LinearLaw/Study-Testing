package com.linear.dao;

import com.linear.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 58.8. 账户的DAO接口
 */
@Repository
public interface AccountDao {

    @Select("select * from account")
    public List<Account> findAll();

    @Insert("insert into account(name,money) values(#{name},#{money})")
    public void saveAccount(Account account);
}
