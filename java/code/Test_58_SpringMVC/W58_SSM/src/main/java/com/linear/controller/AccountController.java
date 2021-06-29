package com.linear.controller;

import com.linear.domain.Account;
import com.linear.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        System.out.println("account controller find all");

        List<Account> list = accountService.findAll();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping("/saveAccount")
    public String saveAccount(Account account){
        System.out.println("account controller save account");
        accountService.saveAccount(account);
        return "redirect:/account/findAll";
    }
}
