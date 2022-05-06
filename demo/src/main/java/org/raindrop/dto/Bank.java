package org.raindrop.dto;

import lombok.Data;

@Data
public class Bank {
    private Integer accountNumber;
    private Double balance;
    private String firstname;
    private String lastname;
    private Integer age;
    private String gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private String state;
}
