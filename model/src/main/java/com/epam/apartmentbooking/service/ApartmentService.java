package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.domain.Apartment;

import java.util.List;

public interface ApartmentService {
    List<Apartment> findAllApartments();

    Apartment findEntityById(Long id);

    boolean remove(Long id);

    boolean create(Apartment apartment);

    boolean update(Apartment apartment);
}
