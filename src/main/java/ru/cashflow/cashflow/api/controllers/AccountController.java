package ru.cashflow.cashflow.api.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.cashflow.cashflow.domain.models.Account;
import ru.cashflow.cashflow.domain.models.User;
import ru.cashflow.cashflow.domain.models.UserGroup;
import ru.cashflow.cashflow.domain.services.AccountService;
import ru.cashflow.cashflow.domain.services.UserService;

@Controller
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    public AccountController(
        AccountService accountService, 
        UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping("/accounts/new")
    public String newAccount(
            @RequestParam("groupId") String groupId,
            Model model) {
        final UserGroup group = userService.findUserGroupById(Long.parseLong(groupId)).orElse(null);

        final List<User> users = userService.findUsersByGroup(group);

        Account account = new Account();
        account.setUserGroup(group);

        model.addAttribute("users", users);
        model.addAttribute("accountForm", account);
        model.addAttribute("group", group);

        return "account_new";
    }

    @PostMapping("accounts/add")
    public String addAccount(
        @ModelAttribute("accountForm") Account account) {
        
        accountService.saveAccount(account);

        return "redirect:/group/" + account.getUserGroup().getId();
    }

}
