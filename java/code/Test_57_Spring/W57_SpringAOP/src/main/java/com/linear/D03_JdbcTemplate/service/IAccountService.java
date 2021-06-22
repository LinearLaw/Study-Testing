package com.linear.D03_JdbcTemplate.service;

import com.linear.D03_JdbcTemplate.domain.Account;

import java.util.List;

/***
 * 57.7、 JdbcTemplate CRUD
 */
public interface IAccountService {
    List<Account> findAll();
    Account findById();
    void updateAccount(Account account);
}
