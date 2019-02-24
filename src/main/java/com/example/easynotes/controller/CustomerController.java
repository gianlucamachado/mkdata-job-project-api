package com.example.easynotes.controller;

import com.example.easynotes.model.Customer;
import com.example.easynotes.repository.CustomerRepository;
import com.example.easynotes.repository.PhoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customer_id) {
        Optional<Customer> customer = customerRepository.findById(customer_id);
        return ResponseEntity.ok().body(customer.get());
    }
}
