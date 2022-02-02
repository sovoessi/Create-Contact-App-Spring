package com.example.contactapp.contact;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(String id) {
        return contactRepository.findById(id);
    }

    public Contact getContactByPhoneNumber(String phoneNumber){
        return contactRepository.findContactByPhoneNumber(phoneNumber);
    }

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Optional<Contact> updateContact(String id, Contact newContact){
       return contactRepository.findById(id)
               .map(oldContact -> {
                   Contact updated = oldContact.updateWith(newContact);
                   return contactRepository.save(updated);
               });
    }

    public void delete(String contactId){
        contactRepository.deleteById(contactId);
    }


}
