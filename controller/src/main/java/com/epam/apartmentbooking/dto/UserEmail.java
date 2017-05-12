package com.epam.apartmentbooking.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserEmail {

    @NotEmpty(message = "{email.error}")
    @Email(message = "{email.error}")
    private String email;

    public UserEmail() {
    }

    public UserEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
