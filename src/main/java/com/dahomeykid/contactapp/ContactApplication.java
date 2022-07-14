package com.dahomeykid.contactapp;

import com.dahomeykid.contactapp.contact.Contact;
import com.dahomeykid.contactapp.contact.ContactRepository;
import com.dahomeykid.contactapp.contact.ContactType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ContactApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ContactRepository contactRepository){
        return args -> {
            Contact pierre = new Contact(
                    "Pierre",
                    "Brosnan",
                    "0102030405",
                    ContactType.BUSINESS
            );

            Contact paul = new Contact(
                    "Paul",
                    "DeTarse",
                    "0203010408",
                    ContactType.FRIEND
            );
            Contact jacques = new Contact(
                    "Jacques",
                    "Salman",
                    "0789104084",
                    ContactType.FAMILY
            );

            // drop all hotels
            contactRepository.deleteAll();

            List<Contact> contacts = Arrays.asList(jacques, paul, pierre);
            contactRepository.saveAll(contacts);

        };
    }

}
