package com.linear.service;

import com.linear.domain.Account;

import java.util.List;

public interface IAccountService {

    List<Account> findAll();

    void saveAccount(Account account);
}
