package ru.cashflow.cashflow.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "redirect:/user-groups";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // @RequestMapping("/error")
    // public String error() {
    //     return "error";
    // }
}
