package ru.cashflow.cashflow.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.cashflow.cashflow.domain.models.Category;
import ru.cashflow.cashflow.domain.models.UserGroup;
import ru.cashflow.cashflow.domain.services.CategoryService;
import ru.cashflow.cashflow.domain.services.UserService;

@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    public CategoryController(
        CategoryService categoryService, 
        UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/categories/new")
    public String newCategory(
            @RequestParam("groupId") String groupId,
            Model model) {
        final UserGroup group = userService.findUserGroupById(Long.parseLong(groupId)).orElse(null);

        Category category = new Category();
        category.setUserGroup(group);

        model.addAttribute("categoryForm", category);
        model.addAttribute("group", group);

        return "category_new";
    }

    @PostMapping("categories/add")
    public String addCategory(
        @ModelAttribute("categoryForm") Category category) {
        
        categoryService.saveCategory(category);

        return "redirect:/group/" + category.getUserGroup().getId();
    }

}
