package ru.cashflow.cashflow.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account{
    
    private Long id;
    private String name;
    private boolean isDebt;
    private User user;
    private UserGroup userGroup;

}