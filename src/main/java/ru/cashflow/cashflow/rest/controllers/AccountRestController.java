package ru.cashflow.cashflow.rest.controllers;

import org.springframework.web.bind.annotation.RestController;

import ru.cashflow.cashflow.domain.models.Account;
import ru.cashflow.cashflow.domain.services.AccountService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {
    
    final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.findAccountsByUserGroup(null);
    }
    

}
