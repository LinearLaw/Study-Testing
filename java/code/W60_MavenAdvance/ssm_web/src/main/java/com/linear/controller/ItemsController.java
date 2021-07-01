package com.linear.controller;

import com.linear.domain.Items;
import com.linear.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/detail")
    public String findDetail(Model model){
        Items items = itemsService.findById(1);
        System.out.println(items);

        model.addAttribute("item", items);
        return "itemDetail";
    }

}
