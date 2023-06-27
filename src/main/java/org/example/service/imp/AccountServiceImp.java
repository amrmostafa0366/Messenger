package org.example.service.imp;

import jakarta.transaction.Transactional;
import org.example.error.ConflictException;
import org.example.error.NotFoundException;
import org.example.model.Account;
import org.example.model.Chat;
import org.example.model.Message;
import org.example.repository.AccountRepo;
import org.example.service.AccountService;
import org.example.service.ChatService;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private ChatService chatService;
    @Autowired
    private MessageService messageService;



    @Override
    public Account findById(Long id) {
        if (existById(id)) {
            return accountRepo.findById(id).get();
        } else {
            throw new NotFoundException(String.format("There Is No Record In Our Database Match That Id : %s", id));
        }
    }

    @Override
    public Page<Account> findAll(Pageable page) {
        return accountRepo.findAll(page);
    }

    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public Account insert(Account account) {
        if (existsByEmail(account.getEmail())) {
            throw new ConflictException("The this Author Is Already Found In Our DataBase");
        }
        return accountRepo.save(account);
    }

    @Override
    public Account update(Long id, Account account) {
        return accountRepo.save(account);
    }

    @Override
    public void delete(Long id) {
        if (existById(id)) {
            accountRepo.deleteById(id);
        } else {
            throw new NotFoundException(String.format("There Is No Record In Our Database Match That %s Id", id));
        }
    }

    @Override
    public Pageable paginationCheck(Optional<Integer> pageSize, Optional<Integer> pageNumber, Optional<String> sortBy, String entity) {
        if ((pageNumber.isPresent() && pageNumber.get() < 0) ||
                (pageSize.isPresent() && pageSize.get() < 1)
        ) {
            return null;
        }
        Sort sort;
        if (sortBy.isPresent()) {
            if (isValidProperty(sortBy.get(), entity)) {
                sort = Sort.by(Sort.Direction.DESC, sortBy.get());
            } else {
                return null;
            }
        } else {
            sort = Sort.by(Sort.Direction.DESC, "id");
        }

        return PageRequest.of(pageNumber.orElse(0), pageSize.orElse(10), sort);
    }

    @Override
    public boolean isValidProperty(String property, String entity) {
        try {
            entity.getClass().getDeclaredField(property);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    @Transactional
    @Override
    public Chat addChat(Long account1Id, Long account2Id) {
        Account account1 = accountRepo.findById(account1Id).orElseThrow(() -> new NotFoundException("Account not found with id: " + account1Id));
        Account account2 = accountRepo.findById(account2Id).orElseThrow(() -> new NotFoundException("Account not found with id: " + account2Id));
        Chat chat = chatService.findByAccounts(account1, account2);

        if (chat != null) {
            return chat;
        } else {
            chat = new Chat();
            chat.setName(account1.getName() + "-" + account2.getName());
            chat.addAccount(account1);
            chat.addAccount(account2);

            Chat insertedChat = chatService.insert(chat);
            account1.addChat(insertedChat);
            account2.addChat(insertedChat);

            accountRepo.save(account1);
            accountRepo.save(account2);

            return insertedChat;
        }
    }

    @Override
    public void deleteChat(Long accountId, Long chaId) {
        if (existById(accountId) && chatService.existsById(chaId)) {
            Account account = accountRepo.findById(accountId).get();
            Chat chat = chatService.findById(chaId);
            if (accountRepo.existsByIdAndChats(accountId, chat)) {
                account.removeChat(chat);
                accountRepo.save(account);
            } else {
                throw new NotFoundException(String.format("There Is No Chat In Our Database Match That Account Id "));

            }
        } else {
            throw new NotFoundException(String.format("There Is No Record In Our Database Match That Id "));
        }
    }

    @Transactional
    @Override
    public Chat sendMessage(Long account1Id, Long account2Id, Message message) {
        Account account1 = findById(account1Id);
        Account account2 = findById(account2Id);
        message.setSender(account1);
        Chat chat = chatService.findByAccounts(account1, account2);
        System.out.println("abolo" + chat);
        if (chat != null) {
            return chatService.sendMessage(account1, chat, message);
        } else {
            chat = new Chat();
            chat.setName(account1.getName() + "-" + account2.getName());
            chat.addAccount(account1);
            chat.addAccount(account2);

            Chat insertedChat = chatService.insert(chat);
            account1.addChat(insertedChat);
            account2.addChat(insertedChat);
            accountRepo.save(account1);
            accountRepo.save(account2);
        }
        return chatService.sendMessage(account1, chat, message);

    }


    @Override
    public Page<Message> findAllMessages(Long accountId, Long chatId, Pageable page) {
        if (existById(accountId) && chatService.existsById(chatId)) {
            Account account = accountRepo.findById(accountId).get();
            Chat chat = chatService.findById(chatId);
            if (existsByIdAndChats(accountId,chat)) {
                //return chatService.findByChat(chat, page);
                return chatService.findByChat(chatId, page);
            } else {
                return null;
            }
        }
        throw new NotFoundException(String.format("There Is No Record In Our Database Match That Id "));
    }

    @Override
    public void deleteMessage(Long accountId, Long chatId, Long messageId) {
        if (existById(accountId) && chatService.existsById(chatId) && messageService.existById(messageId)) {
            Account account = accountRepo.findById(accountId).get();
            Chat chat = chatService.findById(chatId);
            Message message = messageService.findById(messageId);
            if (existsByIdAndChats(accountId,chat) && message.getSender().equals(account)) {
//                chatService.deleteMessage(account,chatId,messageId);
                message.setContent("deleted");
                messageService.update(message);
            }else{
                throw new NotFoundException("Chat Not Found");
            }
        } else {
            throw new NotFoundException(String.format("There Is No Record In Our Database Match That Id "));
        }
    }

    @Override
    public boolean existsByIdAndChats(Long accountId, Chat chat) {
        return accountRepo.existsByIdAndChats(accountId, chat);
    }

    @Override
    public boolean existById(Long id) {
        return accountRepo.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return accountRepo.existsByEmail(email);
    }

    @Override
    public List<Chat> findChatsById(Long accountId) {
        return accountRepo.findChatsById(accountId);
    }

    @Override
    public Chat findChatByIdAndChatId(Long accountId, Long chatId) {
        return accountRepo.findChatByIdAndChatId(accountId, chatId);
    }
}


