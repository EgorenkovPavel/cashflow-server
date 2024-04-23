package ru.cashflow.cashflow.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.Cashflow;

public interface CashflowRepository extends JpaRepository<Cashflow, Long>{
    
}
