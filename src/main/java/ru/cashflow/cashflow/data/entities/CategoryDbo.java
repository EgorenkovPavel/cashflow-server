package ru.cashflow.cashflow.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Data
@Entity(name = "categories")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CategoryDbo {
    
    @Id
    private final Long id;

    @Column
    private final String name;

    @Column
    private final Type type;
    
    public enum Type {
    INPUT, OUTPUT
    }

    @ManyToOne
    private final UserGroupDbo group;

}