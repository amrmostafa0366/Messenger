package org.example.service.imp;

import org.example.error.NotFoundException;
import org.example.model.Account;
import org.example.model.Chat;
import org.example.model.Message;
import org.example.repository.ChatRepo;
import org.example.service.AccountService;
import org.example.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImp implements ChatService {
    @Autowired
    private ChatRepo chatRepo;
    @Lazy
    @Autowired
    private AccountService accountService;

    @Override
    public Chat findById(Long id) {
        if (existsById(id)) {
            return chatRepo.findById(id).get();
        } else {
            throw new NotFoundException(String.format("There Is No Record In Our Database Match That Id : %s", id));
        }
    }

    @Override
    public Chat insert(Chat chat) {
//        Chat checkedChat = findByAccountsIn(chat.getAccounts());
//        if (checkedChat != null) {
//            return checkedChat;
//        } else {
        return chatRepo.save(chat);
//        }
    }

    @Override
    public Chat findByAccounts(Account account1, Account account2) {
        return chatRepo.findByAccounts(account1,account2);
    }

    @Override
    public boolean existsByAccounts(Account account1 , Account account2) {
        Chat chat = chatRepo.findByAccounts(account1,account2);
        if (chat == null) {
            return true;
        } else {
            return false;
        }

    }

/*
    @Transactional
    @Override
    public Chat addChat(Account account1, Account account2) {
        if (!chatExists(account1, account2) && !chatExists(account1, account2)) {
            chatRepo.save(chat);
            account1.addChat(chat);
            accountService.update(account1.getId(),account1);
            return chat;
        }
        throw new ConflictException("This Chat Is Already Created Before");
    }*/

    @Override
    public boolean existsById(Long id) {
        return chatRepo.existsById(id);
    }

    @Override
    public void deleteChat(Account account1, Long chaId) {

    }

    @Override
    public Chat sendMessage(Account account, Chat chat, Message message) {
        chat.addMessage(message);
        return chatRepo.save(chat);
    }

    @Override
    public void deleteMessage(Account account, Long chatId, Long messageId) {

    }

    @Override
    public Page<Message> findAllMessages(Long userId, Long chatId, Pageable page) {
        return null;
    }

//    @Override
//    public boolean chatExists(Account account1, Account account2) {
//        return chatRepo.existsByAccount1AndAccount2(account1, account2);
//    }
}
