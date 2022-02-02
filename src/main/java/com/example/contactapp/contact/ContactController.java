package com.example.contactapp.contact;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/contacts")
@AllArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public List<Contact> fetchAllContacts(){
        return contactService.getAllContacts();
    }

    @GetMapping("/phone/{phoneNumber}")
    public Contact fetchContactPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        Contact found = contactService.getContactByPhoneNumber(phoneNumber);
        return found;
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
        Contact created = contactService.saveContact(contact);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{contactId}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<Contact> fetchContactById(@PathVariable("contactId") String id){
        Optional<Contact> found = contactService.getContactById(id);
        return ResponseEntity.of(found);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<Contact> updateContact(
            @PathVariable("contactId") String id,
            @RequestBody Contact updatedContact){

        Optional<Contact> updated = contactService.updateContact(id, updatedContact);

        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    Contact created = contactService.saveContact(updatedContact);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{contactId}")
                            .buildAndExpand(created.getId())
                            .toUri();

                    return ResponseEntity.created(location).body(created);
                });
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Contact> delete(@PathVariable("contactId") String id) {
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
