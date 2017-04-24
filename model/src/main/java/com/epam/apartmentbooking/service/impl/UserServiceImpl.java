package com.epam.apartmentbooking.service.impl;

import com.epam.apartmentbooking.dao.UserDAO;
import com.epam.apartmentbooking.domain.User;
import com.epam.apartmentbooking.service.UserService;
import com.epam.apartmentbooking.util.MailUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private SimpleMailMessage template;

    private static final String RESTORE_PASSWORD_MAIL_SUBJECT = "Apartment booking: Restore forgotten password";

    @Override
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    @Override
    public User findEntityById(Long id) {
        return userDAO.findEntityById(id);
    }

    @Override
    public boolean restoreForgottenPassword(String email) {
        Long userId = userDAO.findUserIdByEmail(email);
        if (userId != null){
            String newPassword = RandomStringUtils.random(12,true,true);
            String newHashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            String text = String.format(template.getText(), newPassword);
            userDAO.changeUserPassword(newHashedPassword, userId);
            mailUtil.sendSimpleMessage(email, RESTORE_PASSWORD_MAIL_SUBJECT, text);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeUserPassword(String password, Long id) {
        return userDAO.changeUserPassword(BCrypt.hashpw(password, BCrypt.gensalt()), id);
    }

    @Override
    public boolean remove(Long id) {
        return userDAO.remove(id);
    }

    @Override
    public boolean create(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userDAO.create(user);
    }

    @Override
    public boolean update(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userDAO.update(user);
    }
}
