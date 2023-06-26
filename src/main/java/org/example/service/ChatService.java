package org.example.service;

import org.example.model.Account;
import org.example.model.Chat;
import org.example.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ChatService {

    Chat insert(Chat chat);


    Chat findByAccounts(Account account1, Account account2);

    boolean existsByAccounts(Account account1, Account account2);

    boolean existsById(Long id);

    void deleteChat(Account account1, Long chaId);


    void deleteMessage(Account account, Long chatId, Long messageId);

    Page<Message> findAllMessages(Long userId, Long chatId, Pageable page);

//    boolean chatExists(Account account1, Account account2);

    Chat findById(Long chatId);

    Chat sendMessage(Account account, Chat chat, Message message);

}
