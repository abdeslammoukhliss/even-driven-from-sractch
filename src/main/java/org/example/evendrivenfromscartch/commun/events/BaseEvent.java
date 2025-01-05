package org.example.evendrivenfromscartch.commun.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEvent <T>{
    private T id;
}
