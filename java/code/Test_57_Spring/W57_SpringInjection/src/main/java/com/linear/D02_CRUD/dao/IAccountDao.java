package com.linear.D02_CRUD.dao;

import com.linear.D02_CRUD.domain.Account;

import java.util.List;

public interface IAccountDao {

    /** 查询所有 */
    List<Account> findAll();

    /** 增删改查 */
    Account findById(Integer id);
    void saveAccount(Account account);
    void updateAccount(Account account);
    void deleteById(Integer id);

}
