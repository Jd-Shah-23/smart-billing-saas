package com.jaydeep.backend.service;

import com.jaydeep.backend.entity.User;
import com.jaydeep.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository repo)
    {
        this.userRepository=repo;
    }

    public List<User> getUsers()
    {
        return this.userRepository.findAll();
    }

    public User createUser(User user)
    {
        return userRepository.save(user);
    }

    public User getUserById(Long id)
    {
        Optional<User> optional=this.userRepository.findById(id);
        return optional.orElse(null);

    }
}
