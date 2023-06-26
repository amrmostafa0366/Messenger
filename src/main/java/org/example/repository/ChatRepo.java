package org.example.repository;

import org.example.model.Account;
import org.example.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long> {

    @Query("select c from Chat c where :account1 member of c.accounts and :account2 member of c.accounts")
    Chat findByAccounts(@Param("account1") Account account1, @Param("account2") Account account2);
}

