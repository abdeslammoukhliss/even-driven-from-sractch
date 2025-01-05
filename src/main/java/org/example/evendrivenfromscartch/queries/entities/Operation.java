package org.example.evendrivenfromscartch.queries.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.evendrivenfromscartch.commun.enums.OperationType;

import java.util.Date;

@Entity
@Getter
@Setter

public class Operation {
    @Id @GeneratedValue
    private Long id;
    private Date date;

    private double amount;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Account account;
}
