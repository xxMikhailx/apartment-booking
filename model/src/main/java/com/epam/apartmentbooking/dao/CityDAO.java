package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.domain.City;

import java.util.List;

public interface CityDAO extends GenericDAO<Long, City> {

    List<City> findAllCities();

    @Override
    City findEntityById(Long id);

    @Override
    boolean remove(Long id);

    @Override
    boolean create(City city);

    @Override
    boolean update(City city);
}
