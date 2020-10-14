package com.linear.dao;

import com.linear.domain.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 查找所有的Account记录
     * @return
     */
    List<Account> findAllAccount();
}
