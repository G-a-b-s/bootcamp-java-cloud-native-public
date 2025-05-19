package com.customer;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Customer {
    protected String name;
    protected String surname;
    protected String email;
    protected String cpf;
    protected String phone;
    protected String address;
    protected String city;
    protected String state;
    protected String zip;
    protected String country;

    public Customer(String name, String surname,String cpf){
        this.name = name;
        this.surname = surname;
        this.cpf = cpf;
    }
}
