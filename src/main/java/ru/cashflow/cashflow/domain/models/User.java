package ru.cashflow.cashflow.domain.models;

import lombok.Data;

@Data
public class User {
    private String name;
    private UserGroup group;
}
