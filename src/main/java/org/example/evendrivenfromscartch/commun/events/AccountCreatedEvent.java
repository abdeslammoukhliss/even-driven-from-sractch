package org.example.evendrivenfromscartch.commun.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.evendrivenfromscartch.commun.enums.AccountStatus;

@NoArgsConstructor
@Getter
public class AccountCreatedEvent extends BaseEvent<String>{
    private double initialBalance;
    private String currency;
    private AccountStatus status;
    public AccountCreatedEvent(String id,double initialBalance,String currency){
        super(id);
        this.currency=currency;
        this.initialBalance=initialBalance;
    }
}
