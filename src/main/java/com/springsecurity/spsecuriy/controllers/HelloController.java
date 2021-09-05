package com.springsecurity.spsecuriy.controllers;

import com.springsecurity.spsecuriy.entities.AppUser;
import com.springsecurity.spsecuriy.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    public HelloController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/user")
    public void addUser(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }
}
