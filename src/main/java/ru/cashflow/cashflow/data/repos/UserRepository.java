package ru.cashflow.cashflow.data.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.UserDbo;
import ru.cashflow.cashflow.data.entities.UserGroupDbo;

public interface UserRepository extends JpaRepository<UserDbo, Long>{
    public List<UserDbo> findByGroup(UserGroupDbo group);
}
