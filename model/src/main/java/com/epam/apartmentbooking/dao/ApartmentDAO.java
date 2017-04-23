package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.domain.Apartment;
import com.epam.apartmentbooking.domain.ApartmentCriteria;

import java.util.List;

public interface ApartmentDAO extends GenericDAO<Long, Apartment> {

    List<Apartment> findAllAvailableApartments();

    List<Apartment> findAllApartments();

    @Override
    Apartment findEntityById(Long id);

    List<Apartment> findAllApartmentsByCriteria(ApartmentCriteria criteria);

    @Override
    boolean remove(Long id);

    @Override
    boolean create(Apartment apartment);

    @Override
    boolean update(Apartment apartment);
}
