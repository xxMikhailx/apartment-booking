package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.config.TestConfigHibernate;
import com.epam.apartmentbooking.domain.City;
import com.epam.apartmentbooking.domain.Country;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfigHibernate.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class CityDAOTest {
    @Autowired
    @Qualifier("cityDAO")
    private CityDAO cityDAO;

    @Test
    @DatabaseSetup("/city/city_data.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void findAllUsersTest() throws Exception {
        List<City> cities = cityDAO.findAllCities();
        Assert.assertEquals(10, cities.size());
        Assert.assertEquals("Pinellas Park", cities.get(0).getTitle());
        Assert.assertEquals("China", cities.get(0).getCountry().getTitle());
    }

    @Test
    @DatabaseSetup("/city/city_data.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void findEntityByIdTest() throws Exception {
        City city = cityDAO.findEntityById(1L);
        Assert.assertEquals("Pinellas Park", city.getTitle());
        Assert.assertEquals("China", city.getCountry().getTitle());

    }

    @Test
    @DatabaseSetup("/city/city_data.xml")
    @ExpectedDatabase(value = "/city/city_data_create.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void createTest() throws Exception{
        cityDAO.create(createTestCity());
    }

    @Test
    @DatabaseSetup("/city/city_data.xml")
    @ExpectedDatabase(value = "/city/city_data_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void updateTest() throws Exception{
        cityDAO.update(createTestCity());
    }

    @Test
    @DatabaseSetup("/city/city_data.xml")
    @ExpectedDatabase(value = "/city/city_data_remove.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void removeTest() throws Exception {
        cityDAO.remove(1L);
    }

    private City createTestCity(){
        City city = new City();
        city.setId(1L);
        city.setTitle("Minsk");
        Country country = new Country();
        country.setId(10L);
        country.setTitle("Belarus");
        city.setCountry(country);
        return city;
    }
}
