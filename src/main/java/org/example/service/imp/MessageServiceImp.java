package org.example.service.imp;

import org.example.error.NotFoundException;
import org.example.model.Message;
import org.example.repository.MessageRepo;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImp implements MessageService {
    @Autowired
    private MessageRepo messageRepo;

    @Override
    public Message findById(Long id) {
        if (existById(id)) {
            return messageRepo.findById(id).get();
        } else {
            throw new NotFoundException(String.format("There Is No Record In Our Database Match That Id : %s", id));
        }
    }


    @Override
    public void update(Message message){
        messageRepo.save(message);
    }

    @Override
    public boolean existById(Long messageId) {
        if(messageRepo.existsById(messageId)){
            return true;
        }else {
            return false;
        }
    }


}
