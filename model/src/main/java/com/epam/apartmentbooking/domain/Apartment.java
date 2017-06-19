package com.epam.apartmentbooking.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "APARTMENTS")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "APARTMENTS_SEQ")
    @Column(name = "AP_ID_PK")
    private Long id;

    // TODO: 6/19/2017 Fix relation problem
    @ManyToOne
    @JoinColumn(name = "AP_OWNER_ID", referencedColumnName = "US_ID_PK")
    private Long idOwner;

    @Column(name = "AP_TITLE")
    private String title;

    @Column(name = "AP_DESCRIPTION", length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "AP_TYPE")
    private ApartmentType apartmentType;

    @Column(name = "AP_PRICE")
    private BigDecimal price;

    @Column(name = "AP_MAX_GUEST_NUMBER")
    private int maxGuestNumber;

    @Column(name = "AP_BED_NUMBER")
    private int bedNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "AP_STATUS", length = 300)
    private ApartmentStatus apartmentStatus;

    @Column(name = "AP_ADDRESS")
    private String address;

    @ManyToOne
    @JoinColumn(name = "AP_CITY_ID", referencedColumnName = "CT_ID_PK")
    private City city;

    public Apartment() {
    }

    public Apartment(Long id, Long idOwner, String title, String description, ApartmentType apartmentType, BigDecimal price, int maxGuestNumber, int bedNumber, ApartmentStatus apartmentStatus, String address, City city) {
        this.id = id;
        this.idOwner = idOwner;
        this.title = title;
        this.description = description;
        this.apartmentType = apartmentType;
        this.price = price;
        this.maxGuestNumber = maxGuestNumber;
        this.bedNumber = bedNumber;
        this.apartmentStatus = apartmentStatus;
        this.address = address;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getMaxGuestNumber() {
        return maxGuestNumber;
    }

    public void setMaxGuestNumber(int maxGuestNumber) {
        this.maxGuestNumber = maxGuestNumber;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public ApartmentStatus getApartmentStatus() {
        return apartmentStatus;
    }

    public void setApartmentStatus(ApartmentStatus apartmentStatus) {
        this.apartmentStatus = apartmentStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
