package org.example.evendrivenfromscartch.queries.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.evendrivenfromscartch.commun.enums.AccountStatus;

import java.util.Collection;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Account {
    @Id
    private String id;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private String currency;
    @OneToMany
    private Collection<Operation> operations;
}
