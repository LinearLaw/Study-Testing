package com.linear.D03_JdbcTemplate.dao;

import com.linear.D03_JdbcTemplate.domain.Account;

import java.util.List;

public interface IAccountDao  {
    List<Account> findAll();
    Account findById(Integer id);
    void updateAccount(Account account);
}
