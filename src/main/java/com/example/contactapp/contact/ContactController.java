package com.example.contactapp.contact;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "https://contact-app-v6.herokuapp.com")
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
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact){
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
            @Valid @PathVariable("contactId") String id,
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        Map<String, String> map = new HashMap<>(errors.size());
        errors.forEach((error) -> {
            String key = ((FieldError) error).getField();
            String val = error.getDefaultMessage();
            map.put(key, val);
        });
        return ResponseEntity.badRequest().body(map);
    }
}
