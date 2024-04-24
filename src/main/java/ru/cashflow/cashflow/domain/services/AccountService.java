package ru.cashflow.cashflow.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.cashflow.cashflow.data.repos.AccountRepository;
import ru.cashflow.cashflow.domain.mappers.AccountMapper;
import ru.cashflow.cashflow.domain.mappers.UserGroupMapper;
import ru.cashflow.cashflow.domain.mappers.UserMapper;
import ru.cashflow.cashflow.domain.models.Account;
import ru.cashflow.cashflow.domain.models.UserGroup;

@Service
public class AccountService {
    
    private final AccountRepository accountRepository;
    private final UserMapper userMapper;
    private final UserGroupMapper groupMapper;
    private final AccountMapper accountMapper;


    public AccountService(
        AccountRepository accountRepository, 
        UserMapper userMapper, 
        UserGroupMapper groupMapper, 
        AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.userMapper = userMapper;
        this.groupMapper = groupMapper;
        this.accountMapper = accountMapper;
    }

    public List<Account> findAccountsByUserGroup(UserGroup group){
        return accountRepository.findByUserGroup(groupMapper.toDBO(group))
        .stream()
        .map(account -> accountMapper.toModel(account))
        .toList();
    }

    public List<Account> findAccountsByUserGroup(UserGroup group, boolean isDebt){
        return accountRepository.findByUserGroupAndIsDebt(groupMapper.toDBO(group), isDebt)
        .stream()
        .map(account -> accountMapper.toModel(account))
        .toList();
    }


    public void saveAccount(Account account){
        accountRepository.save(accountMapper.toDBO(account));
    }

}
