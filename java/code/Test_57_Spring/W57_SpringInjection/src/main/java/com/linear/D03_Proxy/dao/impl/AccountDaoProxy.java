package com.linear.D03_Proxy.dao.impl;

import com.linear.D02_CRUD.dao.IAccountDao;
import com.linear.D02_CRUD.domain.Account;
import com.linear.D03_Proxy.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 57.5、如果此时，需要实现转账
 *      A账户给B账户转100，
 *      0、查出A账户的钱
 *      1、A账户的money -100
 *      2、查出B账户的钱
 *      3、B账户的money +100
 *
 *      持久层 - 事务控制支持 + 动态代理模式
 *          需要改一下连接的获取
 */
public class AccountDaoProxy implements IAccountDao {

    private QueryRunner runner;
    public QueryRunner getRunner() { return runner; }
    public void setRunner(QueryRunner runner) { this.runner = runner; }

    /** 加入了事务控制的工具类,这里需要增加 connectionUtils支持
     *      并且,所有的runner操作,第一参数需要带上 connectionUtils.getThreadConnection()
     * */
    private ConnectionUtils connectionUtils;
    public void setConnectionUtils(ConnectionUtils connectionUtils) { this.connectionUtils = connectionUtils; }

    /** 查找所有
     * */
    public List<Account> findAll() {
        try {
            return runner.query(
                    connectionUtils.getThreadConnection(), // 改由事务提供的连接控制
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
                    connectionUtils.getThreadConnection(), // 改由事务提供的连接控制
                    "select * from account where id=?",
                    new BeanHandler<Account>(Account.class),
                    id
            );
        } catch (SQLException e) { throw new RuntimeException(); }
    }
    /** 57.4、根据名称查找 */
    public Account findByName(String name){
        try {
            List<Account> list = runner.query(
                    connectionUtils.getThreadConnection(), // 改由事务提供的连接控制
                    "select * from account where name=?",
                    new BeanListHandler<Account>(Account.class),
                    name
            );
            if(list == null || list.size() == 0){
                return null;
            }
            if(list.size() > 1){
                throw new RuntimeException("结果集不唯一，数据出错");
            }
            return list.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** 保存 */
    public void saveAccount(Account account) {
        try {
            runner.update(
                    connectionUtils.getThreadConnection(), // 改由事务提供的连接控制
                    "insert into account(name,money) value(?,?)",
                    account.getName(),account.getMoney()
            );
        } catch (SQLException e) { e.printStackTrace(); }
    }
    /** 修改 */
    public void updateAccount(Account account) {
        try {
            runner.update(
                    connectionUtils.getThreadConnection(), // 改由事务提供的连接控制
                    "update account set name=?,money=? where id=?",
                    account.getName(),account.getMoney(),account.getId()
            );
        } catch (SQLException e) { e.printStackTrace(); }
    }

    /** 删除 */
    public void deleteById(Integer id) {
        try {
            runner.update(
                    connectionUtils.getThreadConnection(), // 改由事务提供的连接控制
                    "delete from account where id=?",
                    id

            );
        }
        catch (Exception e) { e.printStackTrace(); }
    }


}
