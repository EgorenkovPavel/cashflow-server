package ru.cashflow.cashflow.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.CategoryDbo;
import ru.cashflow.cashflow.data.entities.UserGroupDbo;

import java.util.List;


public interface CategoryRepository extends JpaRepository<CategoryDbo, Long>{

    
    public List<CategoryDbo> findByUserGroup(UserGroupDbo group);
}
