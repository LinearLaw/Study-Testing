package com.linear.D03_JdbcTemplate.service.impl;

import com.linear.D03_JdbcTemplate.domain.Account;
import com.linear.D03_JdbcTemplate.service.IAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * 57.7、 JdbcTemplate CRUD
 */
@Service("accountServiceJdbc")
public class AccountService implements IAccountService {
    public List<Account> findAll() {
        return null;
    }

    public Account findById() {
        return null;
    }

    public void updateAccount(Account account) {

    }

}
