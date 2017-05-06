package com.epam.apartmentbooking.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserCredential {

    @NotNull
    @Size(min = 5, max = 40, message = "Login size must be from 5 to 40 symbols.")
    private String login;

    @NotNull
    @Size(min = 5, max = 40, message = "Password size must be from 5 to 40 symbols.")
    private String password;

    public UserCredential() {
    }

    public UserCredential(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
