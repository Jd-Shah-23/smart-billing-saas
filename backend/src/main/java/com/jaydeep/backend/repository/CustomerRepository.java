package com.jaydeep.backend.repository;

import com.jaydeep.backend.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    public Page<Customer> findByUserUserId( Long id,Pageable pageable);
}
