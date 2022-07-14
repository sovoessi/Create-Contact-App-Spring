package com.dahomeykid.contactapp.contact;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ContactRepository extends MongoRepository<Contact, String > {

    @Query("{phoneNumber:'?0'}")
    Contact findContactByPhoneNumber(String phoneNumber);
}
