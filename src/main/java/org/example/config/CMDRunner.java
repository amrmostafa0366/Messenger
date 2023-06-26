package org.example.config;

import org.example.model.Account;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CMDRunner implements CommandLineRunner {

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {

        if (accountService.findAll().isEmpty()) {
            for (int i = 1; i < 5; i++) {
                accountService.insert(new Account("Account" + i, "Account" + i + "@gmail.com"));
            }
        }

    }
}
