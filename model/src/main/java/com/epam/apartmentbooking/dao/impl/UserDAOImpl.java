package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.UserDAO;
import com.epam.apartmentbooking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String COLUMN_ID = "US_ID_PK";
    private static final String COLUMN_LOGIN = "US_LOGIN";
    private static final String COLUMN_PASSWORD = "US_PASSWORD";
    private static final String COLUMN_EMAIL = "US_EMAIL";
    private static final String COLUMN_NAME = "US_NAME";
    private static final String COLUMN_SURNAME = "US_SURNAME";
    private static final String COLUMN_CREATION_DATE = "US_CREATION_DATE";
    private static final String COLUMN_ROLE = "US_ROLE";

    private static final String DATE_TIME_PATTERN = "uuuu-MM-d HH:mm:ss.S";

    private static final String SQL_SELECT_ALL_USERS ="SELECT US_ID_PK,US_LOGIN,US_PASSWORD,US_EMAIL,US_NAME,US_SURNAME,US_CREATION_DATE,US_ROLE FROM USERS ORDER BY US_ID_PK";
    private static final String SQL_UPDATE_USER_PASSWORD = "UPDATE USERS SET US_PASSWORD = ? WHERE US_ID_PK = ?";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT US_ID_PK,US_LOGIN,US_PASSWORD,US_EMAIL,US_NAME,US_SURNAME,US_CREATION_DATE,US_ROLE FROM USERS WHERE US_ID_PK = ?";
    private static final String SQL_CREATE_USER = "INSERT INTO USERS (US_LOGIN,US_PASSWORD,US_EMAIL,US_NAME,US_SURNAME,US_CREATION_DATE,US_ROLE) VALUES (?,?,?,?,?,TO_DATE(?, 'yyyy-mm-dd'),?)";
    private static final String SQL_UPDATE_USER_BY_ID = "UPDATE USERS SET US_LOGIN = ?,US_PASSWORD = ?,US_EMAIL = ?,US_NAME = ?,US_SURNAME = ?,US_ROLE = ? WHERE US_ID_PK = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM USERS WHERE US_ID_PK = ?";

    @Override
    public List<User> findAllUsers(){
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());
    }

    @Override
    public User findEntityById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID,
                new Object[]{id},
                new UserMapper());
    }

    @Override
    public boolean changeUserPassword(String password, Long id){
        return jdbcTemplate.update(SQL_UPDATE_USER_PASSWORD, password, id) > 0;
    }

    @Override
    public boolean remove(Long id) {
        return jdbcTemplate.update(SQL_DELETE_USER, id) > 0;
    }

    @Override
    public boolean create(User user) {
        return jdbcTemplate.update(SQL_CREATE_USER,
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                LocalDate.now().toString(),
                user.getRole()) > 0;
    }

    @Override
    public boolean update(User user) {
        return jdbcTemplate.update(SQL_UPDATE_USER_BY_ID,
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getRole(),
                user.getId()) > 0;
    }

    private static final class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong(COLUMN_ID));
            user.setLogin(rs.getString(COLUMN_LOGIN));
            user.setPassword(rs.getString(COLUMN_PASSWORD));
            user.setEmail(rs.getString(COLUMN_EMAIL));
            user.setName(rs.getString(COLUMN_NAME));
            user.setSurname(rs.getString(COLUMN_SURNAME));
            user.setCreationDate(LocalDate.parse(rs.getString(COLUMN_CREATION_DATE), DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
            user.setRole(rs.getInt(COLUMN_ROLE));
            return user;
        }
    }
}
