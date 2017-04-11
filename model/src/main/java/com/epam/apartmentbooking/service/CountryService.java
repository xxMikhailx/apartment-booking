package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAllCountries();

    Country findEntityById(Long id);

    boolean remove(Long id);

    boolean create(Country country);

    boolean update(Country country);
}
