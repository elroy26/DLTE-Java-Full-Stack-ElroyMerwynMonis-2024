package com.example.jpaaccount.controllers;

import com.example.jpaaccount.model.AccountHolder;
import com.example.jpaaccount.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountRestApi {
    @Autowired
    AccountService accountService;
    @PostMapping(value="/")
    public AccountHolder apiSave(@RequestBody AccountHolder accountHolder){
        return accountService.callSave(accountHolder);
    }

    @PutMapping(value="/update", consumes = "application/json")
    public AccountHolder apiUpdate(@RequestBody AccountHolder accountHolder){
        return accountService.callSave(accountHolder);
    }
    @GetMapping(value="/readAll")
    public List<AccountHolder> apiAll(){
        return accountService.callFindAll();
    }
}
