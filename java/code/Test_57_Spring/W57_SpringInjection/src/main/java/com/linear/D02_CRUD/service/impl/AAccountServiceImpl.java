package com.linear.D02_CRUD.service.impl;

import com.linear.D02_CRUD.dao.IAccountDao;
import com.linear.D02_CRUD.domain.Account;
import com.linear.D02_CRUD.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * 57.3、注解 - CRUD - 业务层
 */
@Service("aAccountService")
public class AAccountServiceImpl implements IAccountService {
    // 此时可以不写setAccountDao方法了，直接用注入
    @Autowired
    private IAccountDao aAccountDao;

    public List<Account> findAll() {
        return aAccountDao.findAll();
    }

    public Account findById(Integer id) {
        return aAccountDao.findById(id);
    }

    public void saveAccount(Account account) {
        aAccountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
        aAccountDao.updateAccount(account);
    }

    public void deleteById(Integer id) {
        aAccountDao.deleteById(id);
    }
}
