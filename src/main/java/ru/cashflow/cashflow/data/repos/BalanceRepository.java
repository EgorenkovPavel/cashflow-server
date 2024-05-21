package ru.cashflow.cashflow.data.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.cashflow.cashflow.data.entities.BalanceDbo;
import ru.cashflow.cashflow.data.entities.OperationDbo;

public interface BalanceRepository extends JpaRepository<BalanceDbo, Long>{
    public void deleteAllByOperation(OperationDbo operation);

    @Query("SELECT SUM(e.sum) FROM balances e WHERE e.account.id = :accountId")
    public Optional<Integer> getAccountBalance(@Param("accountId") Long accountId);
}
