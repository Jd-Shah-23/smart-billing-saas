package com.jaydeep.backend.controller;

import com.jaydeep.backend.entity.User;
import com.jaydeep.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private  final UserService userService;

    public UserController(UserService userService)
    {
        this.userService=userService;
    }


    @GetMapping("/api/users")
    public List<User> getUsers()
    {
        return userService.getUsers();
    }

    @PostMapping("/api/users")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @GetMapping("/api/users/{id}")
    public User getUserById(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }
}
