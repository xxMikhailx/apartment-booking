package com.epam.apartmentbooking.domain;

import java.math.BigDecimal;

public class ApartmentCreteria {

    private Long idOwner;
    private String title;
    private ApartmentType apartmentType;
    private BigDecimal price;
    private int maxGuestNumber;
    private int bedNumber;
    private ApartmentStatus apartmentStatus;
    private String address;
    private City city;

}
