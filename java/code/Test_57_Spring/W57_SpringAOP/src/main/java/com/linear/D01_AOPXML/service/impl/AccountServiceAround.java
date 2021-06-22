package com.linear.D01_AOPXML.service.impl;

import com.linear.D01_AOPXML.service.IAccountService;

/**
 * 57.5、模拟账户发生的操作
 *      环绕通知
 */
public class AccountServiceAround implements IAccountService {
    public void saveAccount() {
        System.out.println("执行了保存");
    }

    public void updateAccount(int id) {
        System.out.println("执行了更新");
    }

    public int deleteAccount() {
        System.out.println("执行了删除");
        return 0;
    }
}
