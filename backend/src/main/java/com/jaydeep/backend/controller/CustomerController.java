package com.jaydeep.backend.controller;

import com.jaydeep.backend.dto.CustomerResponse;
import com.jaydeep.backend.dto.PageResponse;
import com.jaydeep.backend.dto.UserResponse;
import com.jaydeep.backend.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService)
    {
        this.customerService=customerService;
    }
    @GetMapping("api/customers")
    public ResponseEntity<PageResponse<List<CustomerResponse>>> getAllCustomer(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "customerName") String sortBy,
                                                @RequestParam(defaultValue = "asc") String direction,
                                                @RequestParam(required = false) String customerEmail,
                                                @RequestParam(required = true) @NotNull Long userId)
    {
        return ResponseEntity.ok(this.customerService.getAllCustomer(page,size,sortBy,direction,customerEmail,userId));
    }

}
