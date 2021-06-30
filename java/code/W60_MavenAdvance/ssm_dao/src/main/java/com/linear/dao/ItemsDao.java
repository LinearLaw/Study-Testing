package com.linear.dao;

import com.linear.domain.Items;

import java.util.List;

public interface ItemsDao {
    Items findById(Integer id);
}
