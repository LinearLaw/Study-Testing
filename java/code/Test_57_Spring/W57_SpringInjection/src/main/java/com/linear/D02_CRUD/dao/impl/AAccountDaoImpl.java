package com.linear.D02_CRUD.dao.impl;

import com.linear.D02_CRUD.dao.IAccountDao;
import com.linear.D02_CRUD.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


/***
 * 57.3、注解 - CRUD - 持久层实现类
 *  以下三个注解，作用相同。
 * @Repository 用在持久层，将当前类对象存入spring容器中
 * @Controller
 * @Service
 */
@Repository("aAccountDao")
public class AAccountDaoImpl implements IAccountDao {
    // 此时可以不写setRunner方法了
    @Autowired
    private QueryRunner arunner;


    /** 查找所有
     *      query
     * */
    public List<Account> findAll() {
        try {
            return arunner.query(
                    "select * from account",
                    new BeanListHandler<Account>(Account.class)
            );
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /** 增删改查
     *      Tips：查找 query
     *          增删改 update
     * */
    public Account findById(Integer id) {

        try {
            return arunner.query(
                    "select * from account where id=?",
                    new BeanHandler<Account>(Account.class),
                    id
            );
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void saveAccount(Account account) {
        try {
            arunner.update(
                    "insert into account(name,money) value(?,?)",
                    account.getName(),account.getMoney()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {
        try {
            arunner.update(
                    "update account set name=?,money=? where id=?",
                    account.getName(),account.getMoney(),account.getId()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Integer id) {
        try {
            arunner.update(
                    "delete from account where id=?",
                    id
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
