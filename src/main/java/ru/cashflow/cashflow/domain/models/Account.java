package ru.cashflow.cashflow.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account{
    
    private Long id;
    private String name;
    private boolean isDebt;
    private User owner;
    private UserGroup userGroup;

}