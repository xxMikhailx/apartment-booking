package com.epam.apartmentbooking.service.impl;

import com.epam.apartmentbooking.dao.CityDAO;
import com.epam.apartmentbooking.domain.City;
import com.epam.apartmentbooking.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class CityServiceImpl implements CityService {
    @Autowired
    @Qualifier("cityDAO")
    private CityDAO cityDAO;

    @Override
    public List<City> findAllCities() {
        return cityDAO.findAllCities();
    }

    @Override
    public City findEntityById(Long id) {
        return cityDAO.findEntityById(id);
    }

    @Override
    public boolean remove(Long id) {
        return cityDAO.remove(id);
    }

    @Override
    public boolean create(City city) {
        return cityDAO.create(city);
    }

    @Override
    public boolean update(City city) {
        return cityDAO.update(city);
    }
}
