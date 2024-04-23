package ru.cashflow.cashflow.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
