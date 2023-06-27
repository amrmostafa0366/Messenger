package org.example.service;

import org.example.model.Chat;
import org.example.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    Message findById(Long id);

    void update(Message message);

    boolean existById(Long messageId);
}
