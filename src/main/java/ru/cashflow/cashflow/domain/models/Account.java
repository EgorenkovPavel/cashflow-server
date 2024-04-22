package ru.cashflow.cashflow.domain.models;

import lombok.Data;

@Data
public class Account{
    
    private Long id;
    private String name;
    private boolean isDebt;
    private User owner;

}