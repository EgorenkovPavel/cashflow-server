package ru.cashflow.cashflow.data.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "cashflows")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class Cashflow {
    
    @Id
    private final Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private final Date date;

    @ManyToOne(optional = false)
    private final Operation operation;

    @ManyToOne(optional = false)
    private final Account account;

    @ManyToOne(optional = false)
    private final Category category;

    @Column(nullable = false)
    private final int sum;
}
