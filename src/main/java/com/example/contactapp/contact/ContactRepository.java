package com.example.contactapp.contact;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String > {
}
