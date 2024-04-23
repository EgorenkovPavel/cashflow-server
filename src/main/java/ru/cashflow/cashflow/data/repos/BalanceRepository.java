package ru.cashflow.cashflow.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Long>{
    
}
