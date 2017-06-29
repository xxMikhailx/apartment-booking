package com.epam.apartmentbooking.domain;

import javax.persistence.*;

@Entity
@Table(name = "COUNTRIES", schema = "TEST")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COUNTRIES_SEQ")
    @SequenceGenerator(name = "COUNTRIES_SEQ", sequenceName = "COUNTRIES_SEQ")
    @Column(name = "CN_ID_PK")
    private Long id;

    @Column(name = "CN_TITLE")
    private String title;

    public Country() {
    }

    public Country(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
