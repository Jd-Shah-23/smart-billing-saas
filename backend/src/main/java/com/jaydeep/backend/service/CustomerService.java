package com.jaydeep.backend.service;

import com.jaydeep.backend.dto.CustomerRequest;
import com.jaydeep.backend.dto.CustomerResponse;
import com.jaydeep.backend.dto.PageResponse;
import com.jaydeep.backend.entity.Customer;
import com.jaydeep.backend.entity.User;
import com.jaydeep.backend.exception.ParentNotFoundException;
import com.jaydeep.backend.repository.CustomerRepository;
import com.jaydeep.backend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public CustomerService(CustomerRepository customerRepository,UserRepository userRepository)
    {
        this.customerRepository=customerRepository;
        this.userRepository=userRepository;
    }
    public PageResponse<List<CustomerResponse>> getAllCustomer(int pageNumber,int pageSize,String sortBy,String direction,String customerEmail,Long id)
    {
        Pageable pageable= PageRequest.of(pageNumber,pageSize,direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending());

        Page<Customer> page=this.customerRepository.findByUserUserId(id,pageable);
        List<Customer> customers=page.getContent();
        List<CustomerResponse> customerResponses=customers.stream().map(
                this::maptoResponse
        ).toList();

        return new PageResponse<>(
                "Fetch all customer sucessfully.",
                customerResponses,
                pageNumber,
                pageSize,
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public CustomerResponse addCustomer(CustomerRequest customerRequest,Long userId) {
        CustomerResponse customerResponse;
        Optional<User> optional=this.userRepository.findById(userId);
        if(!optional.isPresent())
        {
            throw new ParentNotFoundException("UserId","User not found with given userId :  " + userId);
        }
        Customer customer=mapToEntity(customerRequest);
        customer.setUser(optional.get());
        return maptoResponse(this.customerRepository.save(customer));
    }

    private CustomerResponse maptoResponse(Customer customer)
    {
        return new CustomerResponse(customer.getCustomerId(),customer.getCustomerName(),customer.getCustomerEmail(),customer.getCustomerCity());
    }

    private Customer mapToEntity(CustomerRequest request)
    {
        return new Customer(request.getCustomerName(),request.getCustomerEmail(), request.getCustomerCity());
    }
}
