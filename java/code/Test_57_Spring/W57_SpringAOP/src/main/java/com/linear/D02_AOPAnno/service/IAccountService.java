package com.linear.D02_AOPAnno.service;

/***
 * 57.6、模拟账户发生的操作 - 注解方式
 */
public interface IAccountService {

    void saveAccount();
    void updateAccount(int id);
    int deleteAccount();
}
