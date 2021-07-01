package com.linear.service.impl;

import com.linear.domain.Items;
import com.linear.service.ItemsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class ItemsServiceImplTest {

    @Test
    public void findById() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath*:spring/applicationContext-*.xml"
        );
        ItemsService itemsService = applicationContext.getBean(ItemsService.class);

        Items items = itemsService.findById(1);
        System.out.println(items);

    }
}