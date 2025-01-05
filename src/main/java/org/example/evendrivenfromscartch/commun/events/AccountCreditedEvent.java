package org.example.evendrivenfromscartch.commun.events;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AccountCreditedEvent extends BaseEvent<String>{
    private double initialBalance;
    private String currency;
    public AccountCreditedEvent(String id, double initialBalance, String currency){
        super(id);
        this.currency=currency;
        this.initialBalance=initialBalance;
    }
}
