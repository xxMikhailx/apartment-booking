package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findEntityById(Long id);

    User loginUser(User user);

    boolean restoreForgottenPassword(String email);

    boolean changeUserPassword(String password, Long id);

    boolean remove(Long id);

    boolean create(User user);

    boolean update(User user);

}
