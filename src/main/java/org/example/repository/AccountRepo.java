package org.example.repository;

import org.example.model.Account;
import org.example.model.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    boolean existsByEmail(String email);

    boolean existsByName(String name);

    boolean existsByChats(Chat chat);
    @Query("select c from Account a join a.chats c where a.id = :id")
    Page<Chat> findAllChats(@Param("id") Long id, Pageable page);

    boolean existsByIdAndChats(Long accountId, Chat chat);
}
