package com.linear.D03_AnnoWithoutXML;

import com.linear.dao.IAccountDao;
import com.linear.domain.Account;
import com.linear.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 57.8  基于注解的AOP事务管理
 *      业务层
 *
 *      哪些service方法需要开启事务控制，就在那个方法上加注解，
 *      在xml中，设置了扫描包
 */
@Service("waccountService")
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true
)
public class WAccountService implements IAccountService {
    @Autowired
    private IAccountDao waccountDao;
    public void setAccountDao(IAccountDao accountDao) { this.waccountDao = accountDao; }

    /** 转账
     *  - 根据名字获取src和target
     *  - src.money - 100
     *  - target.money + 100
     */
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false
    )
    public void transfer(String src, String target, Float money){
        System.out.println("纯注解方式");
        Account su = waccountDao.findAccountByName(src);
        Account tu = waccountDao.findAccountByName(target);

        su.setMoney(su.getMoney() - money);
        tu.setMoney(tu.getMoney() + money);

        waccountDao.updateAccount(su);
        // int i = 1/0; // create a exception
        waccountDao.updateAccount(tu);
    }

    public Account findAccountById(Integer id) {
        return waccountDao.findAccountById(id);
    }
    public Account findAccountByName(String name) {
        return waccountDao.findAccountByName(name);
    }
    public void updateAccount(Account account) {
        waccountDao.updateAccount(account);
    }
}
