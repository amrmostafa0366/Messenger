package org.example.service;

import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    boolean existById(Long messageId);
}
