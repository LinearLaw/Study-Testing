package com.linear.D03_Proxy.service.impl;

import com.linear.D02_CRUD.domain.Account;
import com.linear.D02_CRUD.service.IAccountService;
import com.linear.D03_Proxy.dao.impl.AccountDao;

import java.util.List;

/**
 * 57.4、如果此时，需要实现转账
 *      A账户给B账户转100，
 *      0、查出A账户的钱
 *      1、A账户的money -100
 *      2、查出B账户的钱
 *      3、B账户的money +100
 *
 *      业务层 - 没有事务控制
 */
public class AccountService implements IAccountService {
    private AccountDao accountDao;
    public AccountDao getAccountDao() { return accountDao; }
    public void setAccountDao(AccountDao accountDao) { this.accountDao = accountDao; }

    /** 57.4、sourceName转账money给targetName
     *      sourceName - money -> targetName
     * */
    public void transfer(String sourceName, String targetName, Float money){
        try {
            // 1、找到两个用户
            Account source = accountDao.findByName(sourceName);
            Account target = accountDao.findByName(targetName);

            // 2、分别进行增减money
            source.setMoney(source.getMoney() - money);
            target.setMoney(target.getMoney() + money);

            // 3、保存信息
            accountDao.updateAccount(source);
            accountDao.updateAccount(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 查找所有 */
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
