package com.linear.D03_JdbcTemplate.dao.impl;

import com.linear.D03_JdbcTemplate.dao.IAccountDao;
import com.linear.D03_JdbcTemplate.domain.Account;
import com.linear.D03_JdbcTemplate.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 57.7、 JdbcTemplate CRUD
 */
public class AccountDao implements IAccountDao {

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Account> findAll() {
        List<Account> list = jdbcTemplate.query(
                "select * from account",
                new BeanPropertyRowMapper<Account>(Account.class)
        );
        return list;
    }

    public Account findById(Integer id) {
        List<Account> list = jdbcTemplate.query(
                "select * from account where id = ?",
                new BeanPropertyRowMapper<Account>(Account.class),
                id
        );
        if(list.isEmpty()){return null;}
        if(list.size() > 1){
            throw new RuntimeException("结果集不唯一");
        }

        return list.get(0);
    }
    
    public void updateAccount(Account account) {
        jdbcTemplate.update(
                "update account set name=?,money=? where id=?",
                account.getName(), account.getMoney(),account.getId()
        );
    }

}
