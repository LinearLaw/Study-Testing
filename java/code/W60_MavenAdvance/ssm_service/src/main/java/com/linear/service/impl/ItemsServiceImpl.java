package com.linear.service.impl;

import com.linear.dao.ItemsDao;
import com.linear.domain.Items;
import com.linear.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsDao itemsDao;

    public void setItemsDao(ItemsDao itemsDao) {
        this.itemsDao = itemsDao;
    }

    public Items findById(Integer id) {
        return itemsDao.findById(id);
    }
}
