package com.linear.D01_TxBasic;

import com.linear.dao.IAccountDao;
import com.linear.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 57.8  基于XML的AOP事务管理
 *      持久层
 */
public class AccountDao extends JdbcDaoSupport implements IAccountDao {
    public Account findAccountById(Integer id) {
        List<Account> list = super.getJdbcTemplate().query(
                "select * from account where id=?",
                new BeanPropertyRowMapper<Account>(Account.class),
                id
        );
        if(list == null){ return null; }
        if(list.size() > 1){ throw new RuntimeException("数据出错，结果集不唯一"); }

        return list.get(0);
    }

    public Account findAccountByName(String name) {
        List<Account> list = super.getJdbcTemplate().query(
                "select * from account where name = ?",
                new BeanPropertyRowMapper<Account>(Account.class),
                name
        );
        if(list == null){ return null; }
        if(list.size() > 1){ throw new RuntimeException("数据出错，结果集不唯一"); }

        return list.get(0);
    }

    public void updateAccount(Account account) {
        super.getJdbcTemplate().update(
                "update account set name=?,money=? where id=?",
                account.getName(),account.getMoney(),account.getId()
        );
    }
}
