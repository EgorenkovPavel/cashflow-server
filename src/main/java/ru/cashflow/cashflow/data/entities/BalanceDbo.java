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
@Entity(name = "balances")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class BalanceDbo {
    
    @Id
    private final Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private final Date date;

    @ManyToOne(optional = false)
    private final OperationDbo operation;

    @ManyToOne(optional = false)
    private final AccountDbo account;

    @Column(nullable = false)
    private final int sum;

}
