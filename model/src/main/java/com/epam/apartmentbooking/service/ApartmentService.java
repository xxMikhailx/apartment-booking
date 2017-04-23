package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.domain.Apartment;
import com.epam.apartmentbooking.domain.ApartmentCriteria;

import java.util.List;

public interface ApartmentService {

    List<Apartment> findAllAvailableApartments();

    List<Apartment> findAllApartments();

    Apartment findEntityById(Long id);

    List<Apartment> findAllApartmentsByCriteria(ApartmentCriteria criteria);

    boolean remove(Long id);

    boolean create(Apartment apartment);

    boolean update(Apartment apartment);
}
