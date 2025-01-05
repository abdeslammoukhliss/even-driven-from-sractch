package org.example.evendrivenfromscartch.commun.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CreateAccountCommand extends BaseCommand<String>{
    private double initialBalance;
    private String currency;

    public CreateAccountCommand(String id){
        super(id);

    }
    public CreateAccountCommand(String id,double initialBalance,String currency){
        super(id);
        this.initialBalance=initialBalance;
        this.currency=currency;

    }
}
