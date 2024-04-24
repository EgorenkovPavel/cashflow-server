package ru.cashflow.cashflow.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    
    private Long id;
    private String name;
    private Type type;
    private UserGroup group;

    public enum Type {
        INPUT, OUTPUT
    }
}
