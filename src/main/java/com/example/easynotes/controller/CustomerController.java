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
import java.util.stream.Collectors;

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

    @PutMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customer_id) {

        Optional<Customer> customer = customerRepository.findById(customer_id);

        if (customer.get() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(customer.get());
    }

    @GetMapping("/customers/customer-by-document/{document_one}")
    public List<Customer> getCustomerByDocument(@PathVariable(value = "document_one") String document_one) {

        List<Customer> customers = customerRepository.findAll().stream()
                .filter(customer -> customer.getDocument_one().equals(document_one.toString()))
                .collect(Collectors.toList());

        return customers;
    }

    @PostMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customer_id,
            @Valid @RequestBody Customer customerDetails) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customer_id);
        Customer customer = optionalCustomer.get();

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        customer.setName(customerDetails.getName());
        customer.setEmail(customerDetails.getEmail());
        customer.setDocument_two(customerDetails.getDocument_two());
        customer.setGroup(customerDetails.getGroup());
        customer.setType(customerDetails.getType());
        customer.setIs_active(customerDetails.getIs_active());
        customer.setPhones(customerDetails.getPhones());
        Customer updatedC = customerRepository.save(customer);

        return ResponseEntity.ok(updatedC);

    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable(value = "id") Long customer_id) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customer_id);
        Customer customer = optionalCustomer.get();

        if (customer == null) {

            return ResponseEntity.notFound().build();

        }

        customerRepository.delete(customer);

        return ResponseEntity.ok().build();
    }
}
