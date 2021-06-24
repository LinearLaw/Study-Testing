package com.linear.D01_TxBasic;

import com.linear.dao.IAccountDao;
import com.linear.domain.Account;
import com.linear.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 57.8  基于XML的AOP事务管理
 *      业务层
 */
public class AccountService implements IAccountService {
    private IAccountDao accountDao;
    public void setAccountDao(IAccountDao accountDao) { this.accountDao = accountDao; }

    /** 转账
     *  - 根据名字获取src和target
     *  - src.money - 100
     *  - target.money + 100
     */
    public void transfer(String src, String target, Float money){
        Account su = accountDao.findAccountByName(src);
        Account tu = accountDao.findAccountByName(target);

        su.setMoney(su.getMoney() - money);
        tu.setMoney(tu.getMoney() + money);

        accountDao.updateAccount(su);
        // int i = 1/0; // create a exception
        accountDao.updateAccount(tu);
    }

    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }
    public Account findAccountByName(String name) {
        return accountDao.findAccountByName(name);
    }
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }
}
