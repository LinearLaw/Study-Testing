package com.linear.dao;


import com.linear.domain.FAccount;

import java.util.List;

/**
 * 56.13 延迟加载 - 账户表
 */
public interface FAccountDao {
    // 查询账户表，同时将账户关联的唯一用户也查出来
    List<FAccount> findAll();

    // 传入一个uid，将该uid关联的账户全都查出来
    List<FAccount> findByUid(Integer uid);
}
