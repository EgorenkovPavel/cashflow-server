package ru.cashflow.cashflow.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.UserGroupDbo;

public interface UserGroupRepository extends JpaRepository<UserGroupDbo, Long>{
    
}
