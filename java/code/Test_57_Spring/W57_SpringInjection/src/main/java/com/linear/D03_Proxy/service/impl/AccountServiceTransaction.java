package com.linear.D03_Proxy.service.impl;

import com.linear.D02_CRUD.domain.Account;
import com.linear.D02_CRUD.service.IAccountService;
import com.linear.D03_Proxy.dao.impl.AccountDao;
import com.linear.D03_Proxy.dao.impl.AccountDaoTransaction;
import com.linear.D03_Proxy.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 57.4、如果此时，需要实现转账
 *      A账户给B账户转100，
 *      0、查出A账户的钱
 *      1、A账户的money -100
 *      2、查出B账户的钱
 *      3、B账户的money +100
 *
 *      业务层 - 事务控制支持
 */
public class AccountServiceTransaction implements IAccountService {
    /** Dao的引用 */
    private AccountDaoTransaction accountDao;
    public AccountDaoTransaction getAccountDao() { return accountDao; }
    public void setAccountDao(AccountDaoTransaction accountDao) { this.accountDao = accountDao; }

    /** 事务控制工具类 */
    private TransactionManager txManager;
    public void setTxManager(TransactionManager txManager) { this.txManager = txManager; }

    /** 57.4、sourceName转账money给targetName
     *      sourceName - money -> targetName
     *
     *      加入事务控制
     * */
    public void transfer(String sourceName, String targetName, Float money){
        try {
            txManager.beginTransaction(); // 开启事务

            // 1、找到两个用户
            Account source = accountDao.findByName(sourceName);
            Account target = accountDao.findByName(targetName);

            // 2、分别进行增减money
            source.setMoney(source.getMoney() - money);
            target.setMoney(target.getMoney() + money);

            // 3、保存信息
            accountDao.updateAccount(source);

            // 即使报错, 已经操作的数据会被rollback()
            int i = 1/0;
            accountDao.updateAccount(target);

            txManager.commit(); // 业务操作末尾, 对操作进行提交, 持久化
        } catch (Exception e) {
            txManager.rollback(); // 一旦发生错误, 进行回滚

            e.printStackTrace();
        } finally {
            txManager.release(); // 最后, 将连接进行归还
        }
    }

    /** 查找所有 */
    public List<Account> findAll() {
        try {
            txManager.beginTransaction();
            List<Account> list= accountDao.findAll();

            txManager.commit();
            return list;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException();
        }finally { txManager.release(); }
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
