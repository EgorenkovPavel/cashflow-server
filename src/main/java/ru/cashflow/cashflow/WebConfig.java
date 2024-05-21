package ru.cashflow.cashflow;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ru.cashflow.cashflow.data.repos.AccountByIdConverter;
import ru.cashflow.cashflow.data.repos.CategoryByIdConverter;
import ru.cashflow.cashflow.data.repos.UserByIdConverter;
import ru.cashflow.cashflow.data.repos.UserGroupByIdConverter;
import ru.cashflow.cashflow.domain.services.AccountService;
import ru.cashflow.cashflow.domain.services.CategoryService;
import ru.cashflow.cashflow.domain.services.UserService;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserService userService;
    private final AccountService accountService;
    private final CategoryService categoryService;

    public WebConfig(
        UserService userService, 
        AccountService accountService, 
        CategoryService categoryService) {
        this.userService = userService;
        this.accountService = accountService;
        this.categoryService = categoryService;
    }

    @Override
    public void addFormatters(@SuppressWarnings("null") FormatterRegistry registry) {
        registry.addConverter(new UserGroupByIdConverter(userService)); 
        registry.addConverter(new UserByIdConverter(userService));
        registry.addConverter(new AccountByIdConverter(accountService));
        registry.addConverter(new CategoryByIdConverter(categoryService));
    }
}
