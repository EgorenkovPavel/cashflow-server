package ru.cashflow.cashflow.data.repos;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.cashflow.cashflow.domain.models.User;
import ru.cashflow.cashflow.domain.services.UserService;


@Component
public class UserByIdConverter implements Converter<String, User> {
        private UserService repo;

        public UserByIdConverter(UserService repo) {
                this.repo = repo;
        }

        @Override
        public User convert(String id) {
                return repo.findUserById(Long.parseLong(id)).orElse(null);
        }

}
