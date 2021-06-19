package com.linear.D02_CRUD.service.impl;

import com.linear.D02_CRUD.dao.IAccountDao;
import com.linear.D02_CRUD.domain.Account;
import com.linear.D02_CRUD.service.IAccountService;

import java.util.List;

/**
 * 57.2  XML - CRUD - Service业务层
 */
public class XAccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;
    public void setAccountDao(IAccountDao accountDao){this.accountDao = accountDao;}

    public List<Account> findAll() {
        return accountDao.findAll();
    }

    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public void deleteById(Integer id) {
        accountDao.deleteById(id);
    }
}
