package org.example.evendrivenfromscartch.queries.controllers;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.example.evendrivenfromscartch.commun.queries.GetAccountByIdQuery;
import org.example.evendrivenfromscartch.commun.queries.GetAllAccountsQuery;
import org.example.evendrivenfromscartch.queries.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@Slf4j
public class AccountQueryController {
    @Autowired
    private QueryGateway queryGateway;
    @GetMapping("/allAccounts")
    public List<Account> accountsList(){
        List<Account> response=queryGateway.query(new GetAllAccountsQuery(), ResponseTypes.multipleInstancesOf(Account.class)).join();
        return response;
    }
    @GetMapping("/accounts/{id}")
    public Account accountsList(@PathVariable String id){
        Account response=queryGateway.query(new GetAccountByIdQuery(id), Account.class).join();
        return response;
    }
}
