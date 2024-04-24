package ru.cashflow.cashflow.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.BalanceDbo;

public interface BalanceRepository extends JpaRepository<BalanceDbo, Long>{
    
}
