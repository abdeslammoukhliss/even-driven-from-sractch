package org.example.evendrivenfromscartch.commun.events;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AccountActivitedEvent extends BaseEvent<String>{
    private double initialBalance;
    private String currency;
    public AccountActivitedEvent(String id, double initialBalance, String currency){
        super(id);
        this.currency=currency;
        this.initialBalance=initialBalance;
    }
}
