package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.ApartmentDAO;
import com.epam.apartmentbooking.domain.Apartment;

import java.util.List;

public class ApartmentDAOImpl implements ApartmentDAO {
    @Override
    public List<Apartment> findAllApartments() {
        return null;
    }

    @Override
    public Apartment findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public boolean create(Apartment apartment) {
        return false;
    }

    @Override
    public boolean update(Apartment apartment) {
        return false;
    }
}
