package ru.cashflow.cashflow.data.repos;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.cashflow.cashflow.domain.models.UserGroup;
import ru.cashflow.cashflow.domain.services.UserService;


@Component
public class UserGroupByIdConverter implements Converter<String, UserGroup> {
        private UserService repo;

        public UserGroupByIdConverter(UserService repo) {
                this.repo = repo;
        }

        @Override
        public UserGroup convert(String id) {
                return repo.findUserGroupById(Long.parseLong(id)).orElse(null);
        }

}
