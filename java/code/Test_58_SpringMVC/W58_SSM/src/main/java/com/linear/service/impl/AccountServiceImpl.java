package com.linear.service.impl;

import com.linear.dao.AccountDao;
import com.linear.domain.Account;
import com.linear.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountDao accountDao;

    public List<Account> findAll() {
        System.out.println("account service find all");
        return accountDao.findAll();
    }

    public void saveAccount(Account account){
        accountDao.saveAccount(account);
    }
}
