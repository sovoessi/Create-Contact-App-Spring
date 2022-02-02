package com.example.contactapp.contact;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Document(collection = "contacts")
public class Contact {

    @Id
    private String id;

    @NotNull(message = "First name is required")
    @Pattern(regexp="^[a-zA-Z ]+$", message = "First name must be a string")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Pattern(regexp="^[a-zA-Z ]+$", message = "Last name must be a string")
    private String lastName;

    @NotNull(message = "Phone number is required")
    @Indexed(unique = true)
    private String phoneNumber;

    private ContactType type;

    public Contact(String firstName, String lastName, String phoneNumber, ContactType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }
}
