package ru.cashflow.cashflow.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.CashflowDbo;

public interface CashflowRepository extends JpaRepository<CashflowDbo, Long>{
    
}
