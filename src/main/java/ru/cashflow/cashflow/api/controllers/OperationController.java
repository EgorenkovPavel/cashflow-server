package ru.cashflow.cashflow.api.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.cashflow.cashflow.domain.models.Account;
import ru.cashflow.cashflow.domain.models.Category;
import ru.cashflow.cashflow.domain.models.Operation;
import ru.cashflow.cashflow.domain.models.User;
import ru.cashflow.cashflow.domain.models.UserGroup;
import ru.cashflow.cashflow.domain.services.AccountService;
import ru.cashflow.cashflow.domain.services.CategoryService;
import ru.cashflow.cashflow.domain.services.OperationService;
import ru.cashflow.cashflow.domain.services.UserService;

@Controller
public class OperationController {

    private final OperationService operationService;
    private final UserService userService;
    private final AccountService accountService;
    private final CategoryService categoryService;

    public OperationController(
        OperationService operationService, 
        UserService userService, 
        AccountService accountService, 
        CategoryService categoryService) {
        this.operationService = operationService;
        this.userService = userService;
        this.accountService = accountService;
        this.categoryService = categoryService;
    }

    @GetMapping("/operations/new")
    public String newOperation(
            @RequestParam("groupId") String groupId,
            Model model) {
        final UserGroup group = userService.findUserGroupById(Long.parseLong(groupId)).orElse(null);

        final List<User> users = userService.findUsersByGroup(group);
        final List<Account> accounts = accountService.findAccountsByUserGroup(group);
        final List<Category> categories = categoryService.findCategoriesByUserGroup(group);

        Operation operation = new Operation();
        operation.setUserGroup(group);

        model.addAttribute("users", users);
        model.addAttribute("operationForm", operation);
        model.addAttribute("group", group);
        model.addAttribute("accounts", accounts);
        model.addAttribute("categories", categories);
        model.addAttribute("recAccounts", accounts);

        return "operation_new";
    }

    @PostMapping("operations/add")
    public String addOperation(
        @ModelAttribute("operationForm") Operation operation) {
        
        operationService.saveOperation(operation);

        return "redirect:/group/" + operation.getUserGroup().getId();
    }

    @GetMapping("operations/delete")
    public String deleteOperation(
        @RequestParam("id") String operationId) {
        
        final Operation operation = operationService.findOperationById(Long.parseLong(operationId)).orElse(null);

        operationService.deleteOperation(operation);

        return "redirect:/group/" + operation.getUserGroup().getId();
    }

}
