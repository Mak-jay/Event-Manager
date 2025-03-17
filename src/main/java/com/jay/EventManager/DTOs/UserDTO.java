package com.jay.EventManager.DTOs;

import java.time.LocalDate;

public class UserDTO {

    private String email;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private LocalDate dateOfBirth;

    public UserDTO() {
    }

    public UserDTO(String email, String firstName, String lastName, String mobileNumber, LocalDate dateOfBirth) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
    }


}
