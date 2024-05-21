package ru.cashflow.cashflow.data.repos;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.cashflow.cashflow.domain.models.Account;
import ru.cashflow.cashflow.domain.services.AccountService;


@Component
public class AccountByIdConverter implements Converter<String, Account> {
        private AccountService repo;

        public AccountByIdConverter(AccountService repo) {
                this.repo = repo;
        }

        @Override
        public Account convert(String id) {
                return repo.findAccountById(Long.parseLong(id)).orElse(null);
        }

}
