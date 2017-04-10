package com.epam.apartmentbooking.service.impl;

import com.epam.apartmentbooking.dao.UserDAO;
import com.epam.apartmentbooking.domain.User;
import com.epam.apartmentbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;


    @Override
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    @Override
    public User findEntityById(Long id) {
        return userDAO.findEntityById(id);
    }

    @Override
    public boolean changeUserPassword(String password, Long id) {
        return userDAO.changeUserPassword(password, id);
    }

    @Override
    public boolean remove(Long id) {
        return userDAO.remove(id);
    }

    @Override
    public boolean create(User user) {
        return userDAO.create(user);
    }

    @Override
    public boolean update(User user) {
        return userDAO.update(user);
    }
}
