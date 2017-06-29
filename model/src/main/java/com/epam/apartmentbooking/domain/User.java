package com.epam.apartmentbooking.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "USERS", schema = "TEST")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ")
    @Column(name = "US_ID_PK")
    private Long id;

    @NotNull
    @Size(min = 4, max = 40, message = "{name.size.error}")
    @Column(name = "US_LOGIN", unique = true)
    private String login;

    @NotNull
    @Size(min = 5, max = 40, message = "{password.size.error}")
    @Column(name = "US_PASSWORD", length = 60)
    private String password;

    @NotEmpty(message = "{email.error}")
    @Email(message = "{email.error}")
    @Column(name = "US_EMAIL", unique = true)
    private String email;

    @NotNull
    @Size(min = 2, max = 60, message = "{first.name.size.error}")
    @Column(name = "US_NAME")
    private String name;

    @NotNull
    @Size(min = 2, max = 60, message = "{surname.size.error}")
    @Column(name = "US_SURNAME")
    private String surname;

    @Column(name = "US_CREATION_DATE")
    private LocalDate creationDate;

    @Column(name = "US_ROLE")
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
