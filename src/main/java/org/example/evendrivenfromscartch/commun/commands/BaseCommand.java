package org.example.evendrivenfromscartch.commun.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@AllArgsConstructor
public abstract class BaseCommand <T>{
    @TargetAggregateIdentifier
    private T id;

}
