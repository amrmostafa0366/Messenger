package org.example.repository;

import org.example.model.Account;
import org.example.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ChatRepo extends JpaRepository<Chat,Long> {


    Chat findByAccountsIn(Set<Account> accounts);
}
