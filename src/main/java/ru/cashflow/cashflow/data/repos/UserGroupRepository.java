package ru.cashflow.cashflow.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long>{
    
}
