package org.example.repository;

import org.example.model.Account;
import org.example.model.Chat;
import org.example.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long> {

    @Query("select c from Chat c where :account1 member of c.accounts and :account2 member of c.accounts")
    Chat findByAccounts(@Param("account1") Account account1, @Param("account2") Account account2);

    //
//    @Query("select m From Message Where Chat c.id = :chatId")
//    Page<Message> findMessagesByChatId(@Param("chatId") Long chatId);
    @Query("SELECT m FROM Chat c JOIN c.messages m WHERE c.id = :chatId")
    Page<Message> findMessagesById(@Param("chatId")Long chatId, Pageable page);

}

