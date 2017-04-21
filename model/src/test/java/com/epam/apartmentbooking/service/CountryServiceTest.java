package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.dao.impl.CountryDAOImpl;
import com.epam.apartmentbooking.domain.Country;
import com.epam.apartmentbooking.service.impl.CountryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {
    @Mock
    private CountryDAOImpl countryDAO;

    @InjectMocks
    private CountryServiceImpl countryService;

    private Country testCountry;
    private List<Country> testCountries;

    @Before
    public void doBefore(){
        testCountry = new Country();
        testCountry.setId(1L);
        testCountry.setTitle("testTitle");

        Country testCountry2 = new Country();
        testCountry2.setId(2L);
        testCountry2.setTitle("testTitle2");

        testCountries = new ArrayList<>();
        testCountries.add(testCountry);
        testCountries.add(testCountry2);
    }

    @Test
    public void findAllCountriesTest(){
        when(countryService.findAllCountries()).thenReturn(testCountries);

        List<Country> actualCountries = countryService.findAllCountries();

        assertEquals(testCountries, actualCountries);
        verify(countryDAO, times(1)).findAllCountries();
    }

    @Test
    public void findEntityByIdTest(){
        when(countryService.findEntityById(anyLong())).thenReturn(testCountry);

        Country actualCountry = countryService.findEntityById(1L);

        assertEquals(testCountry, actualCountry);
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(countryDAO, times(1)).findEntityById(captor.capture());
        assertEquals(testCountry.getId(), captor.getValue());
    }

    @Test
    public void removeTest(){
        when(countryService.remove(anyLong())).thenReturn(true);

        Boolean actualResult = countryService.remove(1L);

        assertTrue(actualResult);
        ArgumentCaptor<Long> captorId = ArgumentCaptor.forClass(Long.class);
        verify(countryDAO, times(1)).remove(captorId.capture());
    }

    @Test
    public void createTest(){
        when(countryService.create(any(Country.class))).thenReturn(true);

        Boolean actualResult = countryService.create(testCountry);

        assertTrue(actualResult);
        ArgumentCaptor<Country> captorCountry = ArgumentCaptor.forClass(Country.class);
        verify(countryDAO, times(1)).create(captorCountry.capture());
    }

    @Test
    public void updateTest(){
        when(countryService.update(any(Country.class))).thenReturn(true);

        Boolean actualResult = countryService.update(testCountry);

        assertTrue(actualResult);
        ArgumentCaptor<Country> captorCountry = ArgumentCaptor.forClass(Country.class);
        verify(countryDAO, times(1)).update(captorCountry.capture());
    }
}
