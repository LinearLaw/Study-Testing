package com.linear.dao;

import com.linear.domain.ERole;

import java.util.List;

/**
 * 56.10 多对多
 *
 */
public interface ERoleDao {

    /**
     * 56.10 查找role表，将所有的role关联的user也查出来
     */
    List<ERole> findAll();
}
