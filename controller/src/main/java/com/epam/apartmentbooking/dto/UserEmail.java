package com.epam.apartmentbooking.dto;

import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserEmail {

    @NotNull
    @Email(message = "Must be a valid email.")
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
