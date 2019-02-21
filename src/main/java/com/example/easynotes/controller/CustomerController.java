package com.example.easynotes.controller;

import com.example.easynotes.model.Customer;
import com.example.easynotes.model.Phone;
import com.example.easynotes.repository.CustomerRepository;
import com.example.easynotes.repository.PhoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Set;

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

        // get set of phones to save
        Set<Phone> phones = customer.getPhones();

        // for each phone save them
        for (Phone phone : phones) {
            phone = phoneRepository.save(phone);
        }

        // return customerRepository.save(customer);
        return customer;
    }
}
