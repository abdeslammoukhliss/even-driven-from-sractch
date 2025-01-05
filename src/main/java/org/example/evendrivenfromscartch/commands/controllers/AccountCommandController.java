package org.example.evendrivenfromscartch.commands.controllers;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.example.evendrivenfromscartch.commun.commands.CreateAccountCommand;
import org.example.evendrivenfromscartch.commun.commands.CreditAccountCommand;
import org.example.evendrivenfromscartch.commun.commands.DebitAccountCommand;
import org.example.evendrivenfromscartch.commun.dto.CreateAccountRequestDTO;
import org.example.evendrivenfromscartch.commun.dto.CreditAccountRequestDTO;
import org.example.evendrivenfromscartch.commun.dto.DebitedAccountRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/account")

public class AccountCommandController {
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    EventStore eventStore;
    @PostMapping("/create")
    public CompletableFuture<String> createAccount(CreateAccountRequestDTO request){
       return commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.getInitialBalance(),
                request.getCurrency()
        ));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/eventStore/{accountId}")
    public Stream eventStore(@PathVariable String accountId){
        return eventStore.readEvents(accountId).asStream();
    }
    @PutMapping("/credit")
    public CompletableFuture<String> creditAccount(CreditAccountRequestDTO request){
        return commandGateway.send(new CreditAccountCommand(
                request.getAccountId(),
                request.getAmount(),
                request.getCurrency()
        ));
    }
    @PutMapping("/debit")
    public CompletableFuture<String> debitAccount(DebitedAccountRequestDTO request){
        return commandGateway.send(new DebitAccountCommand(
                request.getAccountId(),
                request.getAmount(),
                request.getCurrency()
        ));
    }
}
