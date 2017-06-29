package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.config.TestConfigHibernate;
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
public class CountryDAOTest {
    @Autowired
    @Qualifier("countryDAO")
    private CountryDAO countryDAO;

    @Test
    @DatabaseSetup("/country/country_data.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void findAllUsersTest() throws Exception {
        List<Country> countries = countryDAO.findAllCountries();
        Assert.assertEquals(10, countries.size());
        Assert.assertEquals("China", countries.get(0).getTitle());
    }

    @Test
    @DatabaseSetup("/country/country_data.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void findEntityByIdTest() throws Exception {
        Country country = countryDAO.findEntityById(1L);
        Assert.assertEquals("China", country.getTitle());
    }

    @Test
    @DatabaseSetup("/country/country_data.xml")
    @ExpectedDatabase(value = "/country/country_data_create.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void createTest() throws Exception{
        countryDAO.create(createTestCountry());
    }

    @Test
    @DatabaseSetup("/country/country_data.xml")
    @ExpectedDatabase(value = "/country/country_data_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void updateTest() throws Exception{
        countryDAO.update(createTestCountry());
    }

    @Test
    @DatabaseSetup("/country/country_data.xml")
    @ExpectedDatabase(value = "/country/country_data_remove.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void removeTest() throws Exception {
        countryDAO.remove(1L);
    }

    private Country createTestCountry(){
        Country country = new Country();
        country.setId(1L);
        country.setTitle("Sweden");
        return country;
    }

}
