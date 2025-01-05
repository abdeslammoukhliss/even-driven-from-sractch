package org.example.evendrivenfromscartch.commun.commands;

import lombok.Getter;

@Getter
public class CreditAccountCommand extends BaseCommand<String>{
    private double amount;
    private String currency;

    public CreditAccountCommand(String id){
        super(id);

    }
}
