package org.example.service;

import org.example.model.Account;
import org.example.model.Chat;
import org.example.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {
    Account findById(Long id);

    Pageable paginationCheck(Optional<Integer> pageSize, Optional<Integer> pageNumber, Optional<String> sortBy, String account);

    Page<Account> findAll(Pageable page);

    List<Account> findAll();

    Account insert(Account account);

    Account update(Long id, Account account);

    void delete(Long id);

    Chat addChat(Long user1Id, Long user2Id);

    void deleteChat(Long user1Id, Long chatId);

//    Chat sendMessage(Long userId, Long chatId, Message message);
    Chat sendMessage(Long senderId, Long receiverId, Message message);

    boolean existsByIdAndChats(Long accountId, Chat chat);

    void deleteMessage(Long user1Id, Long chatId, Long messageId);

    Page<Message> findAllMessages(Long userId, Long chatId, Pageable page);

    boolean isValidProperty(String property, String entity);

    boolean existById(Long id);

    boolean existsByEmail(String email);

    boolean existsByName(String email);

    boolean existsByChat(Chat chat);

    Page<Chat> findAllChats(Long userId, Pageable page);
}
