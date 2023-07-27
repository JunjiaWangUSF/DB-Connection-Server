package com.example.dbconnectionserver;

import com.example.dbconnectionserver.data.UserEntity;
import com.example.dbconnectionserver.data.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class InitialSetup {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        UserEntity user = new UserEntity(
                1L,
                "test2@test.com",
                "junjia", "wang",
                bCryptPasswordEncoder.encode("aoao"),
                "http://");

        usersRepository.save(user);
    }
}

