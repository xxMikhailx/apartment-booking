package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.CountryDAO;
import com.epam.apartmentbooking.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("countryDAO")
public class CountryDAOImpl implements CountryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String COLUMN_ID = "CN_ID_PK";
    private static final String COLUMN_TITLE = "CN_TITLE";

    private static final String SQL_SELECT_ALL_COUNTRIES ="SELECT CN_ID_PK,CN_TITLE FROM COUNTRIES ORDER BY CN_ID_PK";
    private static final String SQL_SELECT_COUNTRY_BY_ID = "SELECT CN_ID_PK,CN_TITLE FROM COUNTRIES WHERE CN_ID_PK = ?";
    private static final String SQL_CREATE_COUNTRY = "INSERT INTO COUNTRIES (CN_TITLE) VALUES (?)";
    private static final String SQL_UPDATE_COUNTRY_BY_ID = "UPDATE COUNTRIES SET CN_TITLE = ? WHERE CN_ID_PK = ?";
    private static final String SQL_DELETE_COUNTRY = "DELETE FROM COUNTRIES WHERE CN_ID_PK = ?";

    @Override
    public List<Country> findAllCountries() {
        return jdbcTemplate.query(SQL_SELECT_ALL_COUNTRIES, new CountryMapper());
    }

    @Override
    public Country findEntityById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_COUNTRY_BY_ID, new Object[]{id}, new CountryMapper());
    }

    @Override
    public boolean remove(Long id) {
        return jdbcTemplate.update(SQL_DELETE_COUNTRY, id) > 0;
    }

    @Override
    public boolean create(Country country) {
        return jdbcTemplate.update(SQL_CREATE_COUNTRY, country.getTitle()) > 0;
    }

    @Override
    public boolean update(Country country) {
        return jdbcTemplate.update(SQL_UPDATE_COUNTRY_BY_ID, country.getTitle(), country.getId()) > 0;
    }

    private static final class CountryMapper implements RowMapper<Country> {

        public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
            Country country = new Country();
            country.setId(rs.getLong(COLUMN_ID));
            country.setTitle(rs.getString(COLUMN_TITLE));
            return country;
        }
    }
}
