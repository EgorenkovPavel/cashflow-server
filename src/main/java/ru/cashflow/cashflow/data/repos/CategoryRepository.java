package ru.cashflow.cashflow.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.CategoryDbo;

public interface CategoryRepository extends JpaRepository<CategoryDbo, Long>{
    
}
