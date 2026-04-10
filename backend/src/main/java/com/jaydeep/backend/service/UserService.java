package com.jaydeep.backend.service;

import com.jaydeep.backend.dto.UserRequest;
import com.jaydeep.backend.dto.UserResponse;
import com.jaydeep.backend.entity.User;
import com.jaydeep.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository repo)
    {
        this.userRepository=repo;
    }

    public List<UserResponse> getUsers()
    {
        List<User> users=this.userRepository.findAll();
        List<UserResponse> userResponse=new ArrayList<>();

        for (User user : users) {
            userResponse.add(mapToResponse(user));
        }

        return userResponse;
    }

    public UserResponse createUser(UserRequest userRequest)
    {
        return mapToResponse(userRepository.save(mapToEntity(userRequest)));
    }

    public UserResponse getUserById(Long id)
    {
        Optional<User> optional=this.userRepository.findById(id);
        UserResponse userResponse=null;
        if(optional.isPresent())
        {
            userResponse=new UserResponse(optional.get().getId(), optional.get().getName(), optional.get().getEmail());
        }
        return userResponse;

    }

    public UserResponse updateUserById(Long id,UserRequest userRequest)
    {

        Optional<User> optional=this.userRepository.findById(id);
        if(optional.isPresent())
        {
            User upUser=optional.get();
            upUser.setName(userRequest.getName());
            upUser.setEmail(userRequest.getEmail());
            return mapToResponse(this.userRepository.save(upUser));
        }
        return null;
    }

    public boolean deleteById(Long id)
    {
        Optional<User> optional=this.userRepository.findById(id);
        if(optional.isPresent())
        {
            this.userRepository.deleteById(id);
            return true;
        }
        else
        {
            return false;
        }
    }


    private User mapToEntity(UserRequest request)
    {
        return new User(request.getName(), request.getEmail());
    }

    private UserResponse mapToResponse(User user)
    {
        return new UserResponse(user.getId(),user.getName(),user.getEmail());
    }
}
