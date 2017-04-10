package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.domain.Country;

import java.util.List;

public interface CountryDAO extends GenericDAO<Long,Country> {

    List<Country> findAllCountries();

    @Override
    Country findEntityById(Long id);

    @Override
    boolean remove(Long id);

    @Override
    boolean create(Country country);

    @Override
    boolean update(Country country);
}
