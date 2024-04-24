package ru.cashflow.cashflow.domain.mappers;

import org.springframework.stereotype.Component;

import ru.cashflow.cashflow.data.entities.AccountDbo;
import ru.cashflow.cashflow.domain.models.Account;

@Component
public class AccountMapper implements DBOMapper<Account, AccountDbo> {

    private final UserMapper userMapper;
    private final UserGroupMapper groupMapper;

    public AccountMapper(
        UserGroupMapper groupMapper, 
        UserMapper userMapper) {
        this.userMapper = userMapper;
        this.groupMapper = groupMapper;
    }

    @Override
    public AccountDbo toDBO(Account model) {
        return new AccountDbo(
            model.getId(),
            model.getName(),
            model.isDebt(),
            userMapper.toDBO(model.getUser()),
            groupMapper.toDBO(model.getUserGroup())
        );
    }

    @Override
    public Account toModel(AccountDbo dbo) {
        return new Account(
            dbo.getId(),
            dbo.getName(),
            dbo.isDebt(),
            userMapper.toModel(dbo.getUser()),
            groupMapper.toModel(dbo.getUserGroup())
        );
    }
    
}
