package ru.cashflow.cashflow;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ru.cashflow.cashflow.data.repos.UserGroupByIdConverter;
import ru.cashflow.cashflow.domain.services.UserService;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserService userService;

    public WebConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addFormatters(@SuppressWarnings("null") FormatterRegistry registry) {
        registry.addConverter(new UserGroupByIdConverter(userService));  // Регистрация конвертера
    }
}
