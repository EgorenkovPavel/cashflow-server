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
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity(name = "operations")
@AllArgsConstructor()
public class OperationDbo {
    
    @Id
    private final Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private final Date date;

    @Column(nullable = false)
    private final Type type;
    
    public enum Type {
    INPUT, OUTPUT, TRANSFER
    }

    @ManyToOne(optional = false)
    private final AccountDbo account;

    @ManyToOne(optional = true)
    private final CategoryDbo category;

    @ManyToOne(optional = true)
    private final AccountDbo recAccount;

    @Column(nullable = false)
    private final int sum;

    @ManyToOne
    private final UserDbo user;

    @ManyToOne
    private final UserGroupDbo userGroup;
}
