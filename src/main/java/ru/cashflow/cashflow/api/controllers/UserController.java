package ru.cashflow.cashflow.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ru.cashflow.cashflow.domain.models.User;
import ru.cashflow.cashflow.domain.models.UserGroup;
import ru.cashflow.cashflow.domain.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String home(Model model){
        final List<User> users = userService.findAllUsers();
        
        final Map<UserGroup, List<User>> groupedUsers = users.stream()
            .collect(Collectors.groupingBy(User::getGroup));

        final List<UserGroup> groups = userService.findAllGroups(); 

        model.addAttribute("groupedUsers", groupedUsers); // Передача сгруппированных пользователей
        model.addAttribute("groups", groups);
        model.addAttribute("userForm", new User());
        
        return "users";
    }
    
    @PostMapping("users/add")
    public String addUser(@ModelAttribute("userForm") User user) {
        System.out.println(user.getGroup().toString());
        
        userService.saveUser(user);
        
        return "redirect:/users";
    }
    
}