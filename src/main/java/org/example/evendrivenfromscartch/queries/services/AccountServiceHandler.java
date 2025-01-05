package org.example.evendrivenfromscartch.queries.services;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.example.evendrivenfromscartch.commun.enums.AccountStatus;
import org.example.evendrivenfromscartch.commun.enums.OperationType;
import org.example.evendrivenfromscartch.commun.events.AccountActivatedEvent;
import org.example.evendrivenfromscartch.commun.events.AccountCreatedEvent;
import org.example.evendrivenfromscartch.commun.events.AccountDebitedEvent;
import org.example.evendrivenfromscartch.commun.queries.GetAccountByIdQuery;
import org.example.evendrivenfromscartch.commun.queries.GetAllAccountsQuery;
import org.example.evendrivenfromscartch.queries.entities.Account;
import org.example.evendrivenfromscartch.queries.entities.Operation;
import org.example.evendrivenfromscartch.queries.repositories.AccountRepository;
import org.example.evendrivenfromscartch.queries.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountServiceHandler {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationRepository operationRepository;

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent){
        log.info("account created event  recievd"+accountCreatedEvent.getId());
         accountRepository.save(new Account(
                 accountCreatedEvent.getId(),
                 accountCreatedEvent.getInitialBalance(),
                 accountCreatedEvent.getStatus(),
                 accountCreatedEvent.getCurrency(),
                null
         ));
    }

    @EventHandler
    public  void on(AccountActivatedEvent event){
        log.info("account activeted event  recievd"+event.getId());
        Account account=accountRepository.findById(event.getId()).get();
        account.setStatus(AccountStatus.ACTIVATED);
        accountRepository.save(account);

    }
    @EventHandler
    public  void on(AccountDebitedEvent event){
        Account account=accountRepository.findById(event.getId()).get();
        account.setBalance(account.getBalance()+event.getAmount());
        accountRepository.save(account);
        Operation operation=new Operation();
        operation.setAccount(account);
        operation.setAmount(event.getAmount());
        operation.setOperationType(OperationType.DEBIT);
        operationRepository.save(operation);
    }
    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query){
        log.info("get all acounts query");
        return accountRepository.findAll();
    }
    @QueryHandler
    public Account on(GetAccountByIdQuery query){
        log.info("get all acounts query");
        return accountRepository.findById(query.getId()).get();
    }
}
