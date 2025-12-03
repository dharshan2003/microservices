package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.User;
import com.example.repo.UserRepo;
import com.example.service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepo repo;

    // GET http://localhost:8082/user/1
    @GetMapping("/{userId}")
    public Mono<User> getUserById(@PathVariable("userId") Integer userId) {
        return service.getUserById(userId);
    }

    // GET http://localhost:8082/user/all
    @GetMapping("/all")
    public Flux<User> getAllUsers() {
        return repo.findAll();
    }
}
