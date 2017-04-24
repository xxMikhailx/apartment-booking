package com.epam.apartmentbooking.domain;

public class City {
    private Long id;
    private String title;
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
