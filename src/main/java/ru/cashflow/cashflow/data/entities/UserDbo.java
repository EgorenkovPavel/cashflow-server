package ru.cashflow.cashflow.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@Data
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDbo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(nullable = false)
    private final String name;

    @Column(nullable = false)
    private final String password;

    @Column(nullable = false)
    private final String email;

    @ManyToOne
    private final UserGroupDbo group;
}
