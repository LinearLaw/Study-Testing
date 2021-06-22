package com.linear.D02_AOPAnno.service.impl;

import com.linear.D02_AOPAnno.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * 57.6、模拟账户发生的操作 - 注解方式
 *      环绕通知
 */
@Service("accountServiceAroundAnno")
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
