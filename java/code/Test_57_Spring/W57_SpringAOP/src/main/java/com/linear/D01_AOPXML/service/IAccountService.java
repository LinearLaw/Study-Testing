package com.linear.D01_AOPXML.service;

/***
 * 57.5、模拟账户发生的操作
 */
public interface IAccountService {

    void saveAccount();
    void updateAccount(int id);
    int deleteAccount();
}
