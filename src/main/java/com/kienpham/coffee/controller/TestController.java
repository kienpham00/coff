package com.kienpham.coffee.controller;

import com.kienpham.coffee.entity.User;
import com.kienpham.coffee.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Slf4j
public class TestController {
    @Autowired
    UserRepository repo;

    @Transactional
    @PostMapping("/p/acc")
    public void createUser(){
        log.info("heeh");
        User u = User.builder()
                .id(1l)
                .fullName("Pham Trung Kien")
                .role("Manager")
                .userName("kienpham").userEmail("kienpham6828@gmail.com")
                .passWord("$2a$10$xnQhYd8H2xVw793TGajPjeQGdPS.frbtY5XH5nZhnB7oRWBPRFfmy").build();
        repo.save(u);
    }
}
