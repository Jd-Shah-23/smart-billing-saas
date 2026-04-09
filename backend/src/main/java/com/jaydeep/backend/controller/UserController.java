package com.jaydeep.backend.controller;

import com.jaydeep.backend.entity.User;
import com.jaydeep.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository userRepository;
    public UserController(UserRepository repo)
    {
        this.userRepository=repo;
    }

    @PostMapping("/api/users")
    public User createUser(@RequestBody User user)
    {
        userRepository.save(user);
        return user;
    }
}
