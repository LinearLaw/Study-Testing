package com.linear.D02_CRUD.dao.impl;

import com.linear.D02_CRUD.dao.IAccountDao;
import com.linear.D02_CRUD.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 57.2  XML - CRUD - DAO持久层
 */
public class XAccountDaoImpl implements IAccountDao {
    // DBUtils提供QueryRunner操作数据库
    private QueryRunner runner;

    public void setRunner(QueryRunner runner){
        this.runner=runner;
    }

    /** 查找所有
     * */
    public List<Account> findAll() {
        try {
            return runner.query(
                    "select * from account",
                    new BeanListHandler<Account>(Account.class)
            );
        } catch (Exception e) { throw new RuntimeException(); }
    }

    /** 增删改查
     *      Tips：查找 query 增删改 update
     * */
    public Account findById(Integer id) {

        try {
            return runner.query(
                    "select * from account where id=?",
                    new BeanHandler<Account>(Account.class),
                    id
            );
        } catch (SQLException e) { throw new RuntimeException(); }
    }

    public void saveAccount(Account account) {
        try {
            runner.update(
                    "insert into account(name,money) value(?,?)",
                    account.getName(),account.getMoney()
            );
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void updateAccount(Account account) {
        try {
            runner.update(
                    "update account set name=?,money=? where id=?",
                    account.getName(),account.getMoney(),account.getId()
            );
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deleteById(Integer id) {
        try { runner.update( "delete from account where id=?", id ); }
        catch (Exception e) { e.printStackTrace(); }
    }
}
