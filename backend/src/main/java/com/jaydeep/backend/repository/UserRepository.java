package com.jaydeep.backend.repository;

import com.jaydeep.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface UserRepository extends JpaRepository<User,Long> {
    Page<User> findByUserEmail (String userEmail, Pageable pageable);
}
