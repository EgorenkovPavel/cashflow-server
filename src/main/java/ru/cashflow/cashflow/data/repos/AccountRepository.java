package ru.cashflow.cashflow.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
}
