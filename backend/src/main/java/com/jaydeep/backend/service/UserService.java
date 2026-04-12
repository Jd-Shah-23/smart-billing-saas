package com.jaydeep.backend.service;

import com.jaydeep.backend.dto.PageResponse;
import com.jaydeep.backend.dto.UserRequest;
import com.jaydeep.backend.dto.UserResponse;
import com.jaydeep.backend.entity.User;
import com.jaydeep.backend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
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

    public PageResponse<List<UserResponse>> getAllUsers(int pageNumber, int pageSize, String sortBy, String direction, String email)
    {
        List<String> allowedFields = List.of("id", "userName", "userEmail","userCity");
        if (!allowedFields.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }

        Pageable pageable= PageRequest.of(pageNumber,pageSize,direction.equalsIgnoreCase("desc")
                        ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending());

        Page<User> page=email!=null && !email.isBlank()
                        ? this.userRepository.findByUserEmail(email,pageable)
                        : this.userRepository.findAll(pageable);

        List<User> users=page.getContent();
        List<UserResponse> userResponse=users.stream()
                .map(this::mapToResponse)
                .toList();

        return new PageResponse<>(
                "User fetch successfully.",
                userResponse,
                pageNumber,
                pageSize,
                page.getTotalElements(),
                page.getTotalPages()
        );
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
            userResponse=new UserResponse(optional.get().getUserId(), optional.get().getUserName(), optional.get().getUserEmail(),optional.get().getUserCity());
        }
        return userResponse;

    }

    public UserResponse updateUserById(Long id,UserRequest userRequest)
    {

        Optional<User> optional=this.userRepository.findById(id);
        if(optional.isPresent())
        {
            User upUser=optional.get();
            upUser.setUserName(userRequest.getUserName());
            upUser.setUserEmail(userRequest.getUserEmail());
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
        return new User(request.getLoginId(),request.getUserName(), request.getUserEmail(),request.getUserPassword(),request.getUserCity());
    }

    private UserResponse mapToResponse(User user)
    {
        return new UserResponse(user.getUserId(),user.getUserName(),user.getUserEmail(),user.getUserCity());
    }
}
