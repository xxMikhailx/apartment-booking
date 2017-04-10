package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.domain.Apartment;

import java.util.List;

public interface ApartmentDAO extends GenericDAO<Long, Apartment> {

    List<Apartment> findAllApartments();

    @Override
    Apartment findEntityById(Long id);

    @Override
    boolean remove(Long id);

    @Override
    boolean create(Apartment apartment);

    @Override
    boolean update(Apartment apartment);
}
