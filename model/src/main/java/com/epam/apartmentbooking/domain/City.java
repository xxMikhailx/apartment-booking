package com.epam.apartmentbooking.domain;

import javax.persistence.*;

@Entity
@Table(name = "CITIES")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "CITIES_SEQ")
    @Column(name = "CT_ID_PK")
    private Long id;

    @Column(name = "CT_TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name = "CT_COUNTRY_ID", referencedColumnName = "CN_ID_PK")
    private Country country;

    public City() {
    }

    public City(Long id, String title, Country country) {
        this.id = id;
        this.title = title;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
