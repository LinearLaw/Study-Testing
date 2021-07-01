package com.linear.dao;

import com.linear.domain.Items;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class ItemsDaoTest {

    @Test
    public void findById() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext-dao.xml"
        );

        ItemsDao mapper = applicationContext.getBean(ItemsDao.class);
        Items items = mapper.findById(1);
        System.out.println(items);
    }
}