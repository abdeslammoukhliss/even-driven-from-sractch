package org.example.evendrivenfromscartch.commun.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.evendrivenfromscartch.commun.enums.AccountStatus;

@NoArgsConstructor
@Getter
public class AccountActivatedEvent extends BaseEvent<String>{
    private AccountStatus status;
    public AccountActivatedEvent(String id, AccountStatus accountStatus){
        super(id);
        this.status=accountStatus;
    }
}
