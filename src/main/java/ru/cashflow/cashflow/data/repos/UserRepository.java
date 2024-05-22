package ru.cashflow.cashflow.data.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.UserDbo;
import ru.cashflow.cashflow.data.entities.UserGroupDbo;

public interface UserRepository extends JpaRepository<UserDbo, Long>{
    public List<UserDbo> findByGroup(UserGroupDbo group);

    public Optional<UserDbo> findByName(String name);

    public Optional<UserDbo> findByEmail(String email);
}
