package ru.cashflow.cashflow.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.AccountDbo;
import ru.cashflow.cashflow.data.entities.UserGroupDbo;

import java.util.List;


public interface AccountRepository extends JpaRepository<AccountDbo, Long>{
    public List<AccountDbo> findByUserGroup(UserGroupDbo userGroup);

    public List<AccountDbo> findByUserGroupAndIsDebt(UserGroupDbo userGroup, boolean isDebt);
}
