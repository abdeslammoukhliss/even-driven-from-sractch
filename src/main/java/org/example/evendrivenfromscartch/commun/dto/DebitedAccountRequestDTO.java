package org.example.evendrivenfromscartch.commun.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebitedAccountRequestDTO {
    private String accountId;
    private double amount;
    private String currency;
}
