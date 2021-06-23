package com.linear.dao;

import com.linear.domain.Account;

public interface IAccountDao {
    Account findAccountById(Integer id);

    Account findAccountByName(String name);

    void updateAccount(Account account);
}
