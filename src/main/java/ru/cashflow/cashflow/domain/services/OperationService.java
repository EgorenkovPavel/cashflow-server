package ru.cashflow.cashflow.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cashflow.cashflow.data.entities.BalanceDbo;
import ru.cashflow.cashflow.data.entities.CashflowDbo;
import ru.cashflow.cashflow.data.entities.OperationDbo;
import ru.cashflow.cashflow.data.repos.BalanceRepository;
import ru.cashflow.cashflow.data.repos.CashflowRepository;
import ru.cashflow.cashflow.data.repos.OperationRepository;
import ru.cashflow.cashflow.domain.mappers.OperationMapper;
import ru.cashflow.cashflow.domain.mappers.UserGroupMapper;
import ru.cashflow.cashflow.domain.models.Operation;
import ru.cashflow.cashflow.domain.models.UserGroup;

@Service
public class OperationService {

    private final OperationRepository operationRepository;
    private final BalanceRepository balanceRepository;
    private final CashflowRepository cashflowRepository;
    private final UserGroupMapper groupMapper;
    private final OperationMapper operationMapper;

    public OperationService(
            OperationRepository operationRepository,
            UserGroupMapper groupMapper,
            OperationMapper operationMapper,
            BalanceRepository balanceRepository,
            CashflowRepository cashflowRepository) {
        this.operationRepository = operationRepository;
        this.balanceRepository = balanceRepository;
        this.cashflowRepository = cashflowRepository;
        this.groupMapper = groupMapper;
        this.operationMapper = operationMapper;
    }

    public List<Operation> findOperationsByUserGroup(UserGroup group) {
        return operationRepository.findByUserGroup(groupMapper.toDBO(group))
                .stream()
                .map(operation -> operationMapper.toModel(operation))
                .toList();
    }

    public Optional<Operation> findOperationById(Long id) {
        return operationRepository.findById(id)
                .map(operation -> operationMapper.toModel(operation));
    }

    @Transactional
    public void saveOperation(Operation operation) {

        final OperationDbo operationDbo = operationMapper.toDBO(operation);

        if (operation.getId() != null && operationRepository.existsById(operation.getId())) {
            balanceRepository.deleteAllByOperation(operationDbo);
            cashflowRepository.deleteAllByOperation(operationDbo);
        }

        operationRepository.save(operationDbo);

        final Operation.Type operationType = operation.getType();

        switch (operationType) {
            case INPUT:
                balanceRepository.save(new BalanceDbo(
                        null,
                        operationDbo.getDate(),
                        operationDbo,
                        operationDbo.getAccount(),
                        operationDbo.getSum()));

                cashflowRepository.save(new CashflowDbo(
                        null,
                        operationDbo.getDate(),
                        operationDbo,
                        operationDbo.getAccount(),
                        operationDbo.getCategory(),
                        operationDbo.getSum()));
                break;
            case OUTPUT:
                balanceRepository.save(new BalanceDbo(
                        null,
                        operationDbo.getDate(),
                        operationDbo,
                        operationDbo.getAccount(),
                        -1 * operationDbo.getSum()));

                cashflowRepository.save(new CashflowDbo(
                        null,
                        operationDbo.getDate(),
                        operationDbo,
                        operationDbo.getAccount(),
                        operationDbo.getCategory(),
                        operationDbo.getSum()));
                break;
            case TRANSFER:
                balanceRepository.save(new BalanceDbo(
                        null,
                        operationDbo.getDate(),
                        operationDbo,
                        operationDbo.getAccount(),
                        -1 * operationDbo.getSum()));

                balanceRepository.save(new BalanceDbo(
                        null,
                        operationDbo.getDate(),
                        operationDbo,
                        operationDbo.getRecAccount(),
                        operationDbo.getSum()));
                break;
        }

    }

    @Transactional
    public void deleteOperation(Operation operation) {

        final OperationDbo operationDbo = operationMapper.toDBO(operation);

        if (operation.getId() != null && operationRepository.existsById(operation.getId())) {
            balanceRepository.deleteAllByOperation(operationDbo);
            cashflowRepository.deleteAllByOperation(operationDbo);
        }

        operationRepository.delete(operationDbo);
    }
}
