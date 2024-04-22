package ru.cashflow.cashflow.domain.models;

import lombok.Data;

@Data
public class Category {
    
    private Long id;
    private String name;
    private CategoryType type;

}
