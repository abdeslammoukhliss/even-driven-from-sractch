package org.example.evendrivenfromscartch.commun.commands;

import lombok.Getter;

@Getter
public class DebitAccountCommand extends BaseCommand<String>{
    private double initialBalance;
    private String currency;

    public DebitAccountCommand(String id){
        super(id);

    }
}
