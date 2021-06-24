package com.linear.D02_TxAnno;

import com.linear.dao.IAccountDao;
import com.linear.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 57.8  基于注解的AOP事务管理
 *      持久层
 */
@Repository("caccountDao")
public class CAccountDao implements IAccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Account findAccountById(Integer id) {
        List<Account> list = jdbcTemplate.query(
                "select * from account where id=?",
                new BeanPropertyRowMapper<Account>(Account.class),
                id
        );
        if(list == null){ return null; }
        if(list.size() > 1){ throw new RuntimeException("数据出错，结果集不唯一"); }

        return list.get(0);
    }

    public Account findAccountByName(String name) {
        List<Account> list = jdbcTemplate.query(
                "select * from account where name = ?",
                new BeanPropertyRowMapper<Account>(Account.class),
                name
        );
        if(list == null){ return null; }
        if(list.size() > 1){ throw new RuntimeException("数据出错，结果集不唯一"); }

        return list.get(0);
    }

    public void updateAccount(Account account) {
        jdbcTemplate.update(
                "update account set name=?,money=? where id=?",
                account.getName(),account.getMoney(),account.getId()
        );
    }
}
