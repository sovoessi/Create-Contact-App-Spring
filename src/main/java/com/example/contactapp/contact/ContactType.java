package com.example.contactapp.contact;

public enum ContactType {

    FRIEND,
    FAMILY,
    BUSINESS;


    @Override
    public String toString() {

        switch (this){
            case FAMILY:
                return "Family";
            case FRIEND:
                return "Friend";
            case BUSINESS:
                return "Business";
            default:
                return "Unknown";
        }
    }
}
