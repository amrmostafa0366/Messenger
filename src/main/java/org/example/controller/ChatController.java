package org.example.controller;

import org.example.model.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/chat")
public class ChatController {

//    @Autowired
//    private ChatService chatService;
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Chat> findMessagesById(@PathVariable("id") @Min(value = 1) @Max(value = 100) Long id) {
//        Chat result = chatService.findMessagesById(id);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    @GetMapping("")
//    public ResponseEntity<Page<Chat>> findAll(@RequestParam("pageSize") Optional<Integer> pageSize,
//                                                @RequestParam("pageNumber") Optional<Integer> pageNumber,
//                                                @RequestParam("sortBy") Optional<String> sortBy
//    ) {
//        Pageable page = chatService.paginationCheck(pageSize, pageNumber, sortBy, "Chat");
//        if ((page == null)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        Page<Chat> result = chatService.findAll(page);
//        if (result != null && !result.isEmpty()) {
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//        }
//    }
//
//    @PostMapping("")
//    public ResponseEntity<Chat> insert(@Valid @RequestBody Chat chat) {
//        Chat result = chatService.insert(chat);
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Chat> update(@PathVariable Long id,@Valid @RequestBody Chat chat) {
//        Chat result = chatService.update(id, chat);
//        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        chatService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//
//    @GetMapping("/chats")
//    public ResponseEntity<Page<Chat>> findAllChats(@PathVariable("id") Long accountId,
//                                                   @RequestParam("pageSize") Optional<Integer> pageSize,
//                                                   @RequestParam("pageNumber") Optional<Integer> pageNumber,
//                                                   @RequestParam("sortBy") Optional<String> sortBy
//    ) {
//        Pageable page = accountService.paginationCheck(pageSize, pageNumber, sortBy, "Account");
//        if ((page == null)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        Page<Chat> result = accountService.findAllChats(accountId, page);
//        if (result != null && !result.isEmpty()) {
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//        }
//    }

}
