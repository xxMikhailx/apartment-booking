package com.epam.apartmentbooking.dao.impl.hibernate;

import com.epam.apartmentbooking.dao.CityDAO;
import com.epam.apartmentbooking.domain.City;
import com.epam.apartmentbooking.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("cityDAO")
public class CityHibernateDAOImpl implements CityDAO {

    private static final String COLUMN_ID = "CT_ID_PK";
    private static final String COLUMN_TITLE = "CT_TITLE";
    private static final String COLUMN_COUNTRY_ID = "CT_COUNTRY_ID";
    private static final String COLUMN_COUNTRY_TITLE = "CN_TITLE";

    private static final String SQL_SELECT_ALL_CITIES ="SELECT CT_ID_PK,CT_TITLE,CT_COUNTRY_ID,CN_TITLE FROM CITIES JOIN COUNTRIES ON (CITIES.CT_COUNTRY_ID = COUNTRIES.CN_ID_PK) ORDER BY CT_ID_PK";
    private static final String SQL_SELECT_CITY_BY_ID = "SELECT CT_ID_PK,CT_TITLE,CT_COUNTRY_ID,CN_TITLE FROM CITIES JOIN COUNTRIES ON (CITIES.CT_COUNTRY_ID = COUNTRIES.CN_ID_PK) WHERE CT_ID_PK = ?";
    private static final String SQL_CREATE_CITY = "INSERT INTO CITIES (CT_TITLE,CT_COUNTRY_ID) VALUES (?,?)";
    private static final String SQL_UPDATE_CITY_BY_ID = "UPDATE CITIES SET CT_TITLE = ?,CT_COUNTRY_ID = ? WHERE CT_ID_PK = ?";
    private static final String SQL_DELETE_CITY = "DELETE FROM CITIES WHERE CT_ID_PK = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<City> findAllCities() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CITIES, new CityMapper());
    }

    @Override
    public City findEntityById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_CITY_BY_ID, new Object[]{id}, new CityMapper());
    }

    @Override
    public boolean remove(Long id) {
        return jdbcTemplate.update(SQL_DELETE_CITY, id) > 0;
    }

    @Override
    public boolean create(City city) {
        return jdbcTemplate.update(SQL_CREATE_CITY, city.getTitle(), city.getCountry().getId()) > 0;
    }

    @Override
    public boolean update(City city) {
        return jdbcTemplate.update(SQL_UPDATE_CITY_BY_ID, city.getTitle(), city.getCountry().getId(), city.getId()) > 0;
    }

    private static final class CityMapper implements RowMapper<City> {

        public City mapRow(ResultSet rs, int rowNum) throws SQLException {
            City city = new City();
            city.setId(rs.getLong(COLUMN_ID));
            city.setTitle(rs.getString(COLUMN_TITLE));
            Country country = new Country();
            country.setId(rs.getLong(COLUMN_COUNTRY_ID));
            country.setTitle(rs.getString(COLUMN_COUNTRY_TITLE));
            city.setCountry(country);
            return city;
        }
    }
}
