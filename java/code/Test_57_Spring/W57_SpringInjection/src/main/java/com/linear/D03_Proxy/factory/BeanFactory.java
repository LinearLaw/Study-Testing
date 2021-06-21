package com.linear.D03_Proxy.factory;

import com.linear.D02_CRUD.service.IAccountService;
import com.linear.D03_Proxy.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 57.5 工厂模式 + 动态代理
 *      AccountServiceTransaction 太复杂了
 *      直接给原有的 AccountService 进行方法增强
 *
 */
public class BeanFactory {
    private IAccountService accountService;
    private TransactionManager txMangager;

    public void setAccountService(IAccountService accountService) { this.accountService = accountService; }
    public void setTxMangager(TransactionManager txMangager) { this.txMangager = txMangager; }

    /** 在获取accountService时,
     *  利用动态代理, 对service进行增强 */
    public IAccountService getAccountService(){
        // 对当前的accountService进行代理增强
        IAccountService proxyService = (IAccountService) Proxy.newProxyInstance(
                accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 也可以添加一些方法例外,不做增强直接返回
                        if("test".equals(method.getName())){
                            return method.invoke(accountService,args);
                        }

                        // 返回值
                        Object rValue = null;
                        try {
                            // 1. 开启事务
                            txMangager.beginTransaction();

                            // 2. 执行原本要执行的方法
                            rValue = method.invoke(accountService, args);

                            // 3. 事务提交
                            txMangager.commit();
                        } catch (Exception e) {
                            // 4. 发生错误时, 进行回滚
                            txMangager.rollback();
                            e.printStackTrace();
                        } finally {
                            // 5. 结束时, 归还连接
                            txMangager.release();
                        }

                        return rValue;
                    }
                }
        );
        return proxyService;
    }
}
