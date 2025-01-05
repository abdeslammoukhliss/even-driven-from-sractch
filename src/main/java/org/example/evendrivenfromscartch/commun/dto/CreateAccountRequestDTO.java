package org.example.evendrivenfromscartch.commun.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Getter

public class CreateAccountRequestDTO {
    private double initialBalance;
    private String currency;
}
