package ru.cashflow.cashflow.web.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.cashflow.cashflow.domain.models.Operation;

@Data
@AllArgsConstructor
public class OperationTableItem {
    final private Long id;
    final private LocalDateTime date;
    final private String type;
    final private String account;
    final private String analytic;
    final private int sum;
    final private String user;

    public static OperationTableItem fromOperation(Operation operation) {
        return new OperationTableItem(
                operation.getId(),
                operation.getDate(),
                operation.getType().toString(),
                operation.getAccount().getName(),
                operation.getType().equals(Operation.Type.TRANSFER) ? operation.getRecAccount().getName()
                        : operation.getCategory().getName(),
                operation.getSum(),
                operation.getUser().getName());
    }
}
