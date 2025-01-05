package org.example.evendrivenfromscartch.queries.repositories;

import org.example.evendrivenfromscartch.queries.entities.Account;
import org.example.evendrivenfromscartch.queries.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
