package com.epam.apartmentbooking.domain;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;
import java.time.LocalDate;

public class User {
    private Long id;

    @NotNull
    @Size(min = 5, max = 40, message = "Login size must be from 5 to 40 symbols.")
    private String login;

    @NotNull
    @Size(min = 5, max = 40, message = "Password size must be from 5 to 40 symbols.")
    private String password;

    @NotNull
    @Email(message = "Must be a valid email.")
    private String email;

    @NotNull
    @Size(min = 2, max = 60, message = "Name size must be from 2 to 60 symbols.")
    private String name;

    @NotNull
    @Size(min = 2, max = 60, message = "Surname size must be from 2 to 60 symbols.")
    private String surname;

    private LocalDate creationDate;

    private int role;

    public User() {
    }

    public User(Long id, String login, String password, String email, String name, String surname, LocalDate creationDate, int role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.creationDate = creationDate;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
