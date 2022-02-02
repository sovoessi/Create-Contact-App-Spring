package com.example.contactapp.contact;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }
}
