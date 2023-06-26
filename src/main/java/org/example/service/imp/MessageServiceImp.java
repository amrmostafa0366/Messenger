package org.example.service.imp;

import org.example.repository.MessageRepo;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImp implements MessageService {
    @Autowired
    private MessageRepo messageRepo;


    @Override
    public boolean existById(Long messageId) {
        if(messageRepo.existsById(messageId)){
            return true;
        }else {
            return false;
        }
    }

//    @Override
//    public Page<Message> sendMessage(Chat chat, Message message, Pageable page) {
//
//         return messageRepo.findAllByChat(chat,page);
//    }
}
