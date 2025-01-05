package org.example.evendrivenfromscartch.commands.aggregate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.example.evendrivenfromscartch.commun.commands.CreateAccountCommand;
import org.example.evendrivenfromscartch.commun.commands.CreditAccountCommand;
import org.example.evendrivenfromscartch.commun.commands.DebitAccountCommand;
import org.example.evendrivenfromscartch.commun.enums.AccountStatus;
import org.example.evendrivenfromscartch.commun.events.AccountActivatedEvent;
import org.example.evendrivenfromscartch.commun.events.AccountCreatedEvent;
import org.example.evendrivenfromscartch.commun.events.AccountCreditedEvent;
import org.example.evendrivenfromscartch.commun.events.AccountDebitedEvent;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.Stream;

@Aggregate
@NoArgsConstructor
@AllArgsConstructor
public class  AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;
    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand){
          if (createAccountCommand.getInitialBalance()<0) throw new RuntimeException("imposible moins 0");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getInitialBalance(),
                createAccountCommand.getCurrency()
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent){
        this.accountId=accountCreatedEvent.getId();
        this.balance=accountCreatedEvent.getInitialBalance();
        this.currency=accountCreatedEvent.getCurrency();
        this.status=AccountStatus.CREATED;
        AggregateLifecycle.apply(new AccountActivatedEvent(accountCreatedEvent.getId(),AccountStatus.ACTIVATED));
    }
    @EventSourcingHandler
    public  void on(AccountActivatedEvent accountActivatedEvent){
        this.status=accountActivatedEvent.getStatus();
    }

    @CommandHandler
    public  void handle(CreditAccountCommand creditAccountCommand){
        if(creditAccountCommand.getAmount()<0) throw new RuntimeException("impo");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                creditAccountCommand.getId(),
                creditAccountCommand.getAmount(),
                creditAccountCommand.getCurrency()
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent creditedEvent){
        this.accountId=creditedEvent.getId();
        this.balance+=creditedEvent.getAmount();
    }


    @CommandHandler
    public  void handle(DebitAccountCommand debitAccountCommand){
        if(debitAccountCommand.getAmount()<0) throw new RuntimeException("impo");
        AggregateLifecycle.apply(new AccountDebitedEvent(
                debitAccountCommand.getId(),
                debitAccountCommand.getAmount(),
                debitAccountCommand.getCurrency()
        ));
    }
    @EventSourcingHandler
    public void on(AccountDebitedEvent accountDebitedEvent){
        this.accountId=accountDebitedEvent.getId();
        this.balance-=accountDebitedEvent.getAmount();
    }
}
