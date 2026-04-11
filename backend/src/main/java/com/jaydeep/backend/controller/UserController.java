package com.jaydeep.backend.controller;

import com.jaydeep.backend.dto.ApiResponse;
import com.jaydeep.backend.dto.PageResponse;
import com.jaydeep.backend.dto.UserRequest;
import com.jaydeep.backend.dto.UserResponse;
import com.jaydeep.backend.entity.User;
import com.jaydeep.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class UserController {
    private  final UserService userService;

    public UserController(UserService userService)
    {
        this.userService=userService;
    }


    @GetMapping("/api/users")
    public ResponseEntity<PageResponse<List<UserResponse>>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int size,
                                                                        @RequestParam(defaultValue = "name") String sortBy,
                                                                        @RequestParam(defaultValue = "asc") String direction,
                                                                        @RequestParam(required = false) String email)
    {
        return ResponseEntity.ok(
                userService.getAllUsers(page,size,sortBy,direction,email)
        );
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest)
    {
        UserResponse response = userService.createUser(userRequest);
        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable Long id)
    {
        UserResponse response = userService.getUserById(id);

        if (response != null) {
            return ResponseEntity.ok(
                    new ApiResponse<>("User fetch sucessfully",response)
            );
        } else {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>("No user found",null)
            );
        }
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable Long id,@RequestBody UserRequest userRequest)
    {
        UserResponse response=userService.updateUserById(id,userRequest);
        return response!=null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id)
    {
        return userService.deleteById(id) ? ResponseEntity.noContent().build(): ResponseEntity.notFound().build();
    }

}
