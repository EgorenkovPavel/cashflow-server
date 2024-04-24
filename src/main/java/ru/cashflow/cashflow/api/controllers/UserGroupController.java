package ru.cashflow.cashflow.api.controllers;

import ru.cashflow.cashflow.domain.models.User;
import ru.cashflow.cashflow.domain.models.UserGroup;
import ru.cashflow.cashflow.domain.services.UserService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserGroupController {

    private final UserService userService;

    public UserGroupController(UserService userService) {
        this.userService = userService;
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

        model.addAttribute("group", group); 
        model.addAttribute("users", users); 

        return "user_group";
    }
    
}
