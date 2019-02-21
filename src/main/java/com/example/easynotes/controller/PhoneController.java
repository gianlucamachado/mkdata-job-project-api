package com.example.easynotes.controller;

import com.example.easynotes.model.Phone;
import com.example.easynotes.repository.PhoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PhoneController {

    @Autowired
    PhoneRepository phoneRepository;

    @GetMapping("/phones")
    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    @PostMapping("/phones")
    public Phone createPhone(@Valid @RequestBody Phone phone) {
        return phoneRepository.save(phone);
    }
}
