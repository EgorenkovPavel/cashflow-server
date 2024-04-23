package ru.cashflow.cashflow.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
