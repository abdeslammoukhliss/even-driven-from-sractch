package org.example.evendrivenfromscartch.commun.events;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AccountCreditedEvent extends BaseEvent<String>{
    private double amount;
    private String currency;
    public AccountCreditedEvent(String id, double amount, String currency){
        super(id);
        this.currency=currency;
        this.amount=amount;
    }
}
