package org.example.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.example.model.Account;
import org.example.model.Chat;
import org.example.model.Message;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable("id") @Min(value = 1) @Max(value = 100) Long id) {
        Account result = accountService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<Account>> findAll(@RequestParam("pageSize") Optional<Integer> pageSize,
                                                 @RequestParam("pageNumber") Optional<Integer> pageNumber,
                                                 @RequestParam("sortBy") Optional<String> sortBy
    ) {
        Pageable page = accountService.paginationCheck(pageSize, pageNumber, sortBy, "Account");
        if ((page == null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Page<Account> result = accountService.findAll(page);
        if (result != null && !result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }

    @PostMapping("")
    public ResponseEntity<Account> insert(@Valid @RequestBody Account account) {
        Account result = accountService.insert(account);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @Valid @RequestBody Account account) {
        Account result = accountService.update(id, account);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add-chat/{id}")
    public ResponseEntity<Chat> addChat(@PathVariable("id") Long acc1Id, @RequestParam("id") Long acc2Id) {
        Chat result = accountService.addChat(acc1Id, acc2Id);
        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }

    @GetMapping("chat/{id}")
    public ResponseEntity<Chat> findChatByIdAndChatId(@PathVariable("id") Long accountId,
                                                      @RequestParam("chatId") Long chatId) {
        Chat result = accountService.findChatByIdAndChatId(accountId, chatId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("chats/{id}")
    public ResponseEntity<List<Chat>> findChatsById(@PathVariable("id") Long accountId) {
        List<Chat> result = accountService.findChatsById(accountId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete-chat/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable("id") Long accountId, @RequestParam("chatId") Long chatId) {
        accountService.deleteChat(accountId, chatId);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    //    @PostMapping("/send-message/{id}")
//    public ResponseEntity<Chat> sendMessage(@PathVariable("id") Long accountId, @RequestParam("chatId") Long chatId, @RequestBody Message message) {
//
//        Chat result = accountService.sendMessage(accountId, chatId, message);
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }
    @PostMapping("/send-message/{id}")
    public ResponseEntity<Chat> sendMessage(@PathVariable("id") Long senderId, @RequestParam("receiverId") Long receiverId, @RequestBody Message message) {
        Chat result = accountService.sendMessage(senderId, receiverId, message);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-message/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") Long account1Id,
                                              @RequestParam("chatId") Long chatId,
                                              @RequestParam("messageId") Long messageId) {
        accountService.deleteMessage(account1Id, chatId, messageId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/chat-messages/{id}")
    public ResponseEntity<Page<Message>> findAllMessages(@PathVariable("id") Long accountId,
                                                         @RequestParam("chatId") Long chatId,
                                                         @RequestParam("pageSize") Optional<Integer> pageSize,
                                                         @RequestParam("pageNumber") Optional<Integer> pageNumber,
                                                         @RequestParam("sortBy") Optional<String> sortBy
    ) {
        Pageable page = accountService.paginationCheck(pageSize, pageNumber, sortBy, "Chat");
        if ((page == null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Page<Message> result = accountService.findAllMessages(accountId, chatId, page);
        if (result != null && !result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }


}
//add account
//get account
//get accounts
//delete account
//add chat
//get chat
//get chats
//delete chat
//send msg
//delete msg
