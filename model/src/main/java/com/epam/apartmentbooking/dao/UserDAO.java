package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.domain.User;

import java.util.List;

public interface UserDAO extends GenericDAO<Long,User> {

    List<User> findAllUsers();

    @Override
    User findEntityById(Long id);

    Long findUserIdByEmail(String email);

    boolean changeUserPassword(String password, Long id);

    @Override
    boolean remove(Long id);

    @Override
    boolean create(User user);

    @Override
    boolean update(User user);
}
