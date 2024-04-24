package ru.cashflow.cashflow.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.OperationDbo;

public interface OperationRepository extends JpaRepository<OperationDbo, Long>{
    
}
