package com.jaydeep.backend.controller;

import com.jaydeep.backend.dto.*;
import com.jaydeep.backend.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService)
    {
        this.customerService=customerService;
    }
    @GetMapping("/api/customers")
    public ResponseEntity<PageResponse<List<CustomerResponse>>> getAllCustomer(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "customerName") String sortBy,
                                                @RequestParam(defaultValue = "asc") String direction,
                                                @RequestParam(required = false) String customerEmail,
                                                @RequestParam(required = true) @NotNull(message = "UserId required.") Long userId)
    {
        return ResponseEntity.ok(this.customerService.getAllCustomer(page,size,sortBy,direction,customerEmail,userId));
    }

    @PostMapping("/api/addcustomer")
    public ResponseEntity<CustomerResponse> addCustomer(@Valid @RequestBody CustomerRequest customerRequest,@RequestParam Long userId) {
        CustomerResponse response=this.customerService.addCustomer(customerRequest,userId);
        return ResponseEntity.status(CREATED).body(response);
    }

}
