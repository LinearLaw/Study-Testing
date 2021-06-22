package com.linear.D02_AOPAnno.service.impl;

import com.linear.D02_AOPAnno.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * 57.6、模拟账户发生的操作 - 注解方式
 *      前置通知、后置通知、异常通知、最终通知
 *
 */
@Service("accountServiceAnno")
public class AccountServiceImpl implements IAccountService {
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
