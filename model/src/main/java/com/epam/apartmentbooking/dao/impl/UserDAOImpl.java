package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.UserDAO;
import com.epam.apartmentbooking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean remove(Long id) {
        return jdbcTemplate.update("DELETE FROM USERS WHERE US_ID_PK = ?", id) > 0;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }
}
