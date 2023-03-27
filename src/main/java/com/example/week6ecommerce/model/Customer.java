package com.example.week6ecommerce.model;

import lombok.Data;


@Data
public class Customer {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;

    public Customer(String firstName, String lastName, String phoneNumber, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public Customer(){
        super();

    }

}
