package ru.cashflow.cashflow.domain.models;

import lombok.Data;

@Data
public class Operation {
    
    private Long id;
    private Account account;
    private OperationType type;
    private Category category;
    private Account recAccount;
    private int sum;

}
