package ru.cashflow.cashflow.data.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private final LocalDateTime date;

    @ManyToOne(optional = false)
    private final OperationDbo operation;

    @ManyToOne(optional = false)
    private final AccountDbo account;

    @Column(nullable = false)
    private final int sum;

}
