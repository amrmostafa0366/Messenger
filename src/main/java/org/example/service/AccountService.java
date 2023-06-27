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

    Chat addChat(Long account1Id, Long account2Id);

    void deleteChat(Long accountId, Long chatId);

    Chat sendMessage(Long senderId, Long receiverId, Message message);

    void deleteMessage(Long account1Id, Long chatId, Long messageId);

    Page<Message> findAllMessages(Long accountId, Long chatId, Pageable page);

    boolean isValidProperty(String property, String entity);

    boolean existsByIdAndChats(Long accountId, Chat chat);

    boolean existById(Long id);

    boolean existsByEmail(String email);

    List<Chat> findChatsById(Long accountId);

    Chat findChatByIdAndChatId(Long accountId, Long chatId);
}
