package com.example.contactapp.contact;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public List<Contact> fetchAllStudents(){
        return contactService.getAllContacts();
    }
}
