package ru.cashflow.cashflow.domain.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    
    private Long id;
    private LocalDateTime date;
    private Account account;
    private Type type;
    private Category category;
    private Account recAccount;
    private int sum;
    private User user;
    private UserGroup userGroup;

    public enum Type {
        INPUT, OUTPUT, TRANSFER
        }
}
