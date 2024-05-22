package ru.cashflow.cashflow.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ru.cashflow.cashflow.domain.models.Category;
import ru.cashflow.cashflow.domain.models.User;
import ru.cashflow.cashflow.domain.models.UserGroup;
import ru.cashflow.cashflow.domain.services.AccountService;
import ru.cashflow.cashflow.domain.services.CategoryService;
import ru.cashflow.cashflow.domain.services.OperationService;
import ru.cashflow.cashflow.domain.services.UserService;
import ru.cashflow.cashflow.web.models.AccountTableItem;
import ru.cashflow.cashflow.web.models.OperationTableItem;

@Controller
public class UserGroupController {

    private final UserService userService;
    private final AccountService accountService;
    private final CategoryService categoryService;
    private final OperationService operationsService;

    public UserGroupController(
            UserService userService,
            AccountService accountService,
            CategoryService categoryService,
            OperationService operationsService) {
        this.userService = userService;
        this.accountService = accountService;
        this.categoryService = categoryService;
        this.operationsService = operationsService;
    }

    @GetMapping("/user-groups")
    public String viewGroups(Model model) {
        final List<UserGroup> groups = userService.findAllGroups();
        model.addAttribute("groups", groups);
        return "user_group_list";
    }

    @GetMapping("/user-groups/new")
    public String newGroupForm(Model model) {
        model.addAttribute("groupForm", new UserGroup());
        return "user_group_new";
    }

    @PostMapping("/user-groups/add")
    public String addGroup(@ModelAttribute("groupForm") UserGroup group) {
        userService.saveGroup(group);
        return "redirect:/user-group_list";
    }

    @GetMapping("/group/{groupId}")
    public String getGroup(@PathVariable("groupId") Long groupId, Model model) {
        final UserGroup group = userService.findUserGroupById(groupId).orElse(null);
        final List<User> users = userService.findUsersByGroup(group);
        final List<AccountTableItem> accounts = accountService.findAccountsByUserGroup(group, false)
                .stream()
                .map(account -> AccountTableItem.fromAccount(
                        account,
                        accountService.getBalance(account.getId())))
                .toList();
        final List<AccountTableItem> debts = accountService.findAccountsByUserGroup(group, true)
                .stream()
                .map(account -> AccountTableItem.fromAccount(
                        account,
                        accountService.getBalance(account.getId())))
                .toList();
        final List<Category> categories = categoryService.findCategoriesByUserGroup(group);
        final List<OperationTableItem> operations = operationsService.findOperationsByUserGroup(group)
                .stream()
                .map(operation -> OperationTableItem.fromOperation(operation))
                .toList();

        model.addAttribute("group", group);
        model.addAttribute("users", users);
        model.addAttribute("accounts", accounts);
        model.addAttribute("debts", debts);
        model.addAttribute("categories", categories);
        model.addAttribute("operations", operations);

        return "user_group";
    }

}
