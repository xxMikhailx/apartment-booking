package com.epam.apartmentbooking.service.impl;

import com.epam.apartmentbooking.dao.CountryDAO;
import com.epam.apartmentbooking.domain.Country;
import com.epam.apartmentbooking.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class CountryServiceImpl implements CountryService {
    @Autowired
    @Qualifier("countryDAO")
    private CountryDAO countryDAO;

    @Override
    public List<Country> findAllCountries() {
        return countryDAO.findAllCountries();
    }

    @Override
    public Country findEntityById(Long id) {
        return countryDAO.findEntityById(id);
    }

    @Override
    public boolean remove(Long id) {
        return countryDAO.remove(id);
    }

    @Override
    public boolean create(Country country) {
        return countryDAO.create(country);
    }

    @Override
    public boolean update(Country country) {
        return countryDAO.update(country);
    }
}
