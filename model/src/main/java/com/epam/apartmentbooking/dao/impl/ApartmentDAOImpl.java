package com.epam.apartmentbooking.dao.impl;

import com.epam.apartmentbooking.dao.ApartmentDAO;
import com.epam.apartmentbooking.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("apartmentDAO")
public class ApartmentDAOImpl implements ApartmentDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String COLUMN_ID = "AP_ID_PK";
    private static final String COLUMN_OWNER_ID = "AP_OWNER_ID";
    private static final String COLUMN_TITLE = "AP_TITLE";
    private static final String COLUMN_DESCRIPTION = "AP_DESCRIPTION";
    private static final String COLUMN_TYPE = "AP_TYPE";
    private static final String COLUMN_PRICE = "AP_PRICE";
    private static final String COLUMN_MAX_GUEST_NUMBER = "AP_MAX_GUEST_NUMBER";
    private static final String COLUMN_BED_NUMBER = "AP_BED_NUMBER";
    private static final String COLUMN_STATUS = "AP_STATUS";
    private static final String COLUMN_ADDRESS = "AP_ADDRESS";
    private static final String COLUMN_CITY_ID = "AP_CITY_ID";
    private static final String COLUMN_CITY_TITLE = "CT_TITLE";
    private static final String COLUMN_COUNTRY_ID = "CT_COUNTRY_ID";
    private static final String COLUMN_COUNTRY_TITLE = "CN_TITLE";

    private static final String SQL_SELECT_ALL_AVAILABLE_APARTMENTS = "SELECT AP_ID_PK,AP_OWNER_ID,AP_TITLE,AP_DESCRIPTION,AP_TYPE,AP_PRICE,AP_MAX_GUEST_NUMBER,AP_BED_NUMBER,AP_STATUS,AP_ADDRESS,AP_CITY_ID,CT_TITLE,CT_COUNTRY_ID,CN_TITLE FROM APARTMENTS JOIN CITIES ON (APARTMENTS.AP_CITY_ID = CITIES.CT_ID_PK) JOIN COUNTRIES ON (CITIES.CT_COUNTRY_ID = COUNTRIES.CN_ID_PK) WHERE AP_STATUS = ? ORDER BY AP_ID_PK";
    private static final String SQL_SELECT_ALL_APARTMENTS = "SELECT AP_ID_PK,AP_OWNER_ID,AP_TITLE,AP_DESCRIPTION,AP_TYPE,AP_PRICE,AP_MAX_GUEST_NUMBER,AP_BED_NUMBER,AP_STATUS,AP_ADDRESS,AP_CITY_ID,CT_TITLE,CT_COUNTRY_ID,CN_TITLE FROM APARTMENTS JOIN CITIES ON (APARTMENTS.AP_CITY_ID = CITIES.CT_ID_PK) JOIN COUNTRIES ON (CITIES.CT_COUNTRY_ID = COUNTRIES.CN_ID_PK) ORDER BY AP_ID_PK";
    private static final String SQL_SELECT_APARTMENT_BY_ID = "SELECT AP_ID_PK,AP_OWNER_ID,AP_TITLE,AP_DESCRIPTION,AP_TYPE,AP_PRICE,AP_MAX_GUEST_NUMBER,AP_BED_NUMBER,AP_STATUS,AP_ADDRESS,AP_CITY_ID,CT_TITLE,CT_COUNTRY_ID,CN_TITLE FROM APARTMENTS JOIN CITIES ON (APARTMENTS.AP_CITY_ID = CITIES.CT_ID_PK) JOIN COUNTRIES ON (CITIES.CT_COUNTRY_ID = COUNTRIES.CN_ID_PK) WHERE AP_ID_PK = ?";
    private static final String SQL_SELECT_ALL_APARTMENTS_BY_CRITERIA = "SELECT AP_ID_PK,AP_OWNER_ID,AP_TITLE,AP_DESCRIPTION,AP_TYPE,AP_PRICE,AP_MAX_GUEST_NUMBER,AP_BED_NUMBER,AP_STATUS,AP_ADDRESS,AP_CITY_ID,CT_TITLE,CT_COUNTRY_ID,CN_TITLE FROM APARTMENTS LEFT JOIN CITIES ON (APARTMENTS.AP_CITY_ID = CITIES.CT_ID_PK) LEFT JOIN COUNTRIES ON (CITIES.CT_COUNTRY_ID = COUNTRIES.CN_ID_PK) WHERE (AP_TITLE LIKE ? OR ? IS NULL) AND (AP_TYPE=? OR ? IS NULL ) AND (AP_PRICE >= ? OR ? IS NULL) AND (AP_PRICE <= ? OR ? IS NULL) AND (AP_MAX_GUEST_NUMBER >= ? OR ? IS NULL) AND (AP_MAX_GUEST_NUMBER <= ? OR ? IS NULL) AND (AP_BED_NUMBER >= ? OR ? IS NULL) AND (AP_BED_NUMBER <= ? OR ? IS NULL) AND (AP_STATUS = ? OR ? IS NULL) AND (AP_ADDRESS LIKE ? OR ? IS NULL) AND (AP_CITY_ID = ? OR ? IS NULL) AND (CN_ID_PK = ? OR ? IS NULL) ORDER BY AP_ID_PK";
    private static final String SQL_CREATE_APARTMENT = "INSERT INTO APARTMENTS (AP_OWNER_ID,AP_TITLE,AP_DESCRIPTION,AP_TYPE,AP_PRICE,AP_MAX_GUEST_NUMBER,AP_BED_NUMBER,AP_STATUS,AP_ADDRESS,AP_CITY_ID) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_APARTMENT_BY_ID = "UPDATE APARTMENTS SET AP_OWNER_ID = ?,AP_TITLE = ?,AP_DESCRIPTION = ?,AP_TYPE = ?,AP_PRICE = ?,AP_MAX_GUEST_NUMBER = ?,AP_BED_NUMBER = ?,AP_STATUS = ?,AP_ADDRESS = ?,AP_CITY_ID = ? WHERE AP_ID_PK = ?";
    private static final String SQL_DELETE_APARTMENT = "DELETE FROM APARTMENTS WHERE AP_ID_PK = ?";

    @Override
    public List<Apartment> findAllAvailableApartments() {
        return jdbcTemplate.query(SQL_SELECT_ALL_AVAILABLE_APARTMENTS, new Object[]{ApartmentStatus.AVAILABLE.toString()}, new ApartmentMapper());
    }

    @Override
    public List<Apartment> findAllApartments() {
        return jdbcTemplate.query(SQL_SELECT_ALL_APARTMENTS, new ApartmentMapper());
    }

    @Override
    public Apartment findEntityById(Long id) {
        List<Apartment> apartments = jdbcTemplate.query(SQL_SELECT_APARTMENT_BY_ID, new Object[]{id}, new ApartmentMapper());
        return apartments.isEmpty() ? null : apartments.get(0);
    }

    @Override
    public List<Apartment> findAllApartmentsByCriteria(ApartmentCriteria criteria) {
        return jdbcTemplate.query(SQL_SELECT_ALL_APARTMENTS_BY_CRITERIA,
                new Object[]{criteria.getTitle(), criteria.getTitle(),
                        criteria.getApartmentType()==null?null:criteria.getApartmentType().toString(), criteria.getApartmentType()==null?null:criteria.getApartmentType().toString(),
                        criteria.getMinPrice(), criteria.getMinPrice(),
                        criteria.getMaxPrice(), criteria.getMaxPrice(),
                        criteria.getMinGuestNumber(), criteria.getMinGuestNumber(),
                        criteria.getMaxGuestNumber(), criteria.getMaxGuestNumber(),
                        criteria.getMinBedNumber(), criteria.getMinBedNumber(),
                        criteria.getMaxBedNumber(), criteria.getMaxBedNumber(),
                        criteria.getApartmentStatus()==null?null:criteria.getApartmentStatus().toString(), criteria.getApartmentStatus()==null?null:criteria.getApartmentStatus().toString(),
                        criteria.getAddress(), criteria.getAddress(),
                        criteria.getCityId(), criteria.getCityId(),
                        criteria.getCountryId(), criteria.getCountryId()},
                new ApartmentMapper());
    }

    @Override
    public boolean remove(Long id) {
        return jdbcTemplate.update(SQL_DELETE_APARTMENT, id) > 0;
    }

    @Override
    public boolean create(Apartment apartment) {
        return jdbcTemplate.update(SQL_CREATE_APARTMENT,
                apartment.getIdOwner(),
                apartment.getTitle(),
                apartment.getDescription(),
                apartment.getApartmentType().toString(),
                apartment.getPrice(),
                apartment.getMaxGuestNumber(),
                apartment.getBedNumber(),
                apartment.getApartmentStatus().toString(),
                apartment.getAddress(),
                apartment.getCity().getId()) > 0;
    }

    @Override
    public boolean update(Apartment apartment) {
        return jdbcTemplate.update(SQL_UPDATE_APARTMENT_BY_ID,
                apartment.getIdOwner(),
                apartment.getTitle(),
                apartment.getDescription(),
                apartment.getApartmentType().toString(),
                apartment.getPrice(),
                apartment.getMaxGuestNumber(),
                apartment.getBedNumber(),
                apartment.getApartmentStatus().toString(),
                apartment.getAddress(),
                apartment.getCity().getId(),
                apartment.getId()) > 0;
    }

    private static final class ApartmentMapper implements RowMapper<Apartment> {

        public Apartment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Apartment apartment = new Apartment();
            apartment.setId(rs.getLong(COLUMN_ID));
            apartment.setIdOwner(rs.getLong(COLUMN_OWNER_ID));
            apartment.setTitle(rs.getString(COLUMN_TITLE));
            apartment.setDescription(rs.getString(COLUMN_DESCRIPTION));
            apartment.setApartmentType(ApartmentType.valueOf(rs.getString(COLUMN_TYPE)));
            apartment.setPrice(rs.getBigDecimal(COLUMN_PRICE));
            apartment.setMaxGuestNumber(rs.getInt(COLUMN_MAX_GUEST_NUMBER));
            apartment.setBedNumber(rs.getInt(COLUMN_BED_NUMBER));
            apartment.setApartmentStatus(ApartmentStatus.valueOf(rs.getString(COLUMN_STATUS)));
            apartment.setAddress(rs.getString(COLUMN_ADDRESS));
            City city = new City();
            city.setId(rs.getLong(COLUMN_CITY_ID));
            city.setTitle(rs.getString(COLUMN_CITY_TITLE));
            Country country = new Country();
            country.setId(rs.getLong(COLUMN_COUNTRY_ID));
            country.setTitle(rs.getString(COLUMN_COUNTRY_TITLE));
            city.setCountry(country);
            apartment.setCity(city);
            return apartment;
        }
    }
}
