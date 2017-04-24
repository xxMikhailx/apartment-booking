package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.config.TestConfig;
import com.epam.apartmentbooking.domain.*;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class ApartmentDAOTest {
    @Autowired
    @Qualifier("apartmentDAO")
    private ApartmentDAO apartmentDAO;

    @Test
    @DatabaseSetup("/apartment/apartment_data.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void findAllAvailableApartmentsTest() throws Exception {
        List<Apartment> apartments = apartmentDAO.findAllAvailableApartments();
        Assert.assertEquals(6, apartments.size());
        Assert.assertEquals("Opela", apartments.get(0).getTitle());
    }

    @Test
    @DatabaseSetup("/apartment/apartment_data.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void findAllApartmentsTest() throws Exception {
        List<Apartment> apartments = apartmentDAO.findAllApartments();
        Assert.assertEquals(10, apartments.size());
        Assert.assertEquals("Lotstring", apartments.get(0).getTitle());
    }

    @Test
    @DatabaseSetup("/apartment/apartment_data.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void findEntityByIdTest() throws Exception {
        Apartment apartment = apartmentDAO.findEntityById(1L);
        Assert.assertEquals("Lotstring", apartment.getTitle());
    }

    @Test
    @DatabaseSetup("/apartment/apartment_data.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void findAllApartmentsByCriteria() throws Exception {
        List<Apartment> apartments = apartmentDAO.findAllApartmentsByCriteria(createTestCriteria());
        Assert.assertEquals(1, apartments.size());
        Assert.assertEquals("Lotstring", apartments.get(0).getTitle());
    }

    @Test
    @DatabaseSetup("/apartment/apartment_data.xml")
    @ExpectedDatabase(value = "/apartment/apartment_data_create.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void createTest() throws Exception{
        apartmentDAO.create(createTestApartment());
    }

    @Test
    @DatabaseSetup("/apartment/apartment_data.xml")
    @ExpectedDatabase(value = "/apartment/apartment_data_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void updateTest() throws Exception{
        apartmentDAO.update(createTestApartment());
    }

    @Test
    @DatabaseSetup("/apartment/apartment_data.xml")
    @ExpectedDatabase(value = "/apartment/apartment_data_remove.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void removeTest() throws Exception {
        apartmentDAO.remove(1L);
    }

    private Apartment createTestApartment(){
        Apartment apartment = new Apartment();
        apartment.setId(1L);
        apartment.setIdOwner(1L);
        apartment.setTitle("Sonsing");
        apartment.setDescription("In hac habitasse platea dictumst.");
        apartment.setApartmentType(ApartmentType.FLAT);
        apartment.setPrice((BigDecimal.valueOf(44.3245)));
        apartment.setMaxGuestNumber(4);
        apartment.setBedNumber(3);
        apartment.setApartmentStatus(ApartmentStatus.AVAILABLE);
        apartment.setAddress("76994 Macpherson Court");
        Country country = new Country();
        country.setId(1L);
        country.setTitle("China");
        City city = new City();
        city.setId(1L);
        city.setTitle("Pinellas Park");
        city.setCountry(country);
        apartment.setCity(city);
        return apartment;
    }

    private ApartmentCriteria createTestCriteria(){
        ApartmentCriteria criteria = new ApartmentCriteria();
        criteria.setMinGuestNumber(1);
        criteria.setMaxGuestNumber(4);
        criteria.setMinPrice(BigDecimal.valueOf(65L));
        criteria.setApartmentType(ApartmentType.FLAT);
        return criteria;
    }

}
