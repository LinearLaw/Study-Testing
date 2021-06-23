package com.linear.service;

import com.linear.domain.Account;

public interface IAccountService {
    Account findAccountById(Integer id);

    Account findAccountByName(String name);

    void updateAccount(Account account);

    void transfer(String src, String target, Float money);
}
