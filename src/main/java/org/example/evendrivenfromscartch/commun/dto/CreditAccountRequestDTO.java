package org.example.evendrivenfromscartch.commun.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditAccountRequestDTO {
    private String accountId;
    private double amount;
    private String currency;
}
