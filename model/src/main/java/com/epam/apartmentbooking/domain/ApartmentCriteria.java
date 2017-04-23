package com.epam.apartmentbooking.domain;

import java.math.BigDecimal;

public class ApartmentCriteria {
    private String title;
    private ApartmentType apartmentType;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer minGuestNumber;
    private Integer maxGuestNumber;
    private Integer minBedNumber;
    private Integer maxBedNumber;
    private ApartmentStatus apartmentStatus;
    private String address;
    private Long cityId;
    private Long countryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinGuestNumber() {
        return minGuestNumber;
    }

    public void setMinGuestNumber(Integer minGuestNumber) {
        this.minGuestNumber = minGuestNumber;
    }

    public Integer getMaxGuestNumber() {
        return maxGuestNumber;
    }

    public void setMaxGuestNumber(Integer maxGuestNumber) {
        this.maxGuestNumber = maxGuestNumber;
    }

    public Integer getMinBedNumber() {
        return minBedNumber;
    }

    public void setMinBedNumber(Integer minBedNumber) {
        this.minBedNumber = minBedNumber;
    }

    public Integer getMaxBedNumber() {
        return maxBedNumber;
    }

    public void setMaxBedNumber(Integer maxBedNumber) {
        this.maxBedNumber = maxBedNumber;
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
