package ru.cashflow.cashflow.data.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.cashflow.cashflow.data.entities.OperationDbo;
import ru.cashflow.cashflow.data.entities.UserGroupDbo;

public interface OperationRepository extends JpaRepository<OperationDbo, Long>{
    public List<OperationDbo> findByUserGroup(UserGroupDbo userGroup);
}
