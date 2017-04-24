package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.domain.City;

import java.util.List;

public interface CityService {

    List<City> findAllCities();

    City findEntityById(Long id);

    boolean remove(Long id);

    boolean create(City city);

    boolean update(City city);
}
