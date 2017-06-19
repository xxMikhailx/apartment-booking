package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.dao.impl.jdbc.CityDAOImpl;
import com.epam.apartmentbooking.domain.City;
import com.epam.apartmentbooking.domain.Country;
import com.epam.apartmentbooking.service.impl.CityServiceImpl;
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
public class CityServiceTest {
    @Mock
    private CityDAOImpl cityDAO;

    @InjectMocks
    private CityServiceImpl cityService;

    private City testCity;
    private List<City> testCities;

    @Before
    public void doBefore(){
        testCity = new City();
        testCity.setId(1L);
        testCity.setTitle("testTitle");
        Country testCountry = new Country();
        testCountry.setId(1L);
        testCountry.setTitle("testCountryTitle");
        testCity.setCountry(testCountry);

        City testCity2 = new City();
        testCity2.setId(2L);
        testCity2.setTitle("testTitle2");
        Country testCountry2 = new Country();
        testCountry2.setId(2L);
        testCountry2.setTitle("testCountryTitle2");
        testCity2.setCountry(testCountry2);

        testCities = new ArrayList<>();
        testCities.add(testCity);
        testCities.add(testCity2);
    }

    @Test
    public void findAllCitiesTest(){
        when(cityService.findAllCities()).thenReturn(testCities);

        List<City> actualCities = cityService.findAllCities();

        assertEquals(testCities, actualCities);
        verify(cityDAO, times(1)).findAllCities();
    }

    @Test
    public void findEntityByIdTest(){
        when(cityService.findEntityById(anyLong())).thenReturn(testCity);

        City actualCity = cityService.findEntityById(1L);

        assertEquals(testCity, actualCity);
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(cityDAO, times(1)).findEntityById(captor.capture());
        assertEquals(testCity.getId(), captor.getValue());
    }

    @Test
    public void removeTest(){
        when(cityService.remove(anyLong())).thenReturn(true);

        Boolean actualResult = cityService.remove(1L);

        assertTrue(actualResult);
        ArgumentCaptor<Long> captorId = ArgumentCaptor.forClass(Long.class);
        verify(cityDAO, times(1)).remove(captorId.capture());
    }

    @Test
    public void createTest(){
        when(cityService.create(any(City.class))).thenReturn(true);

        Boolean actualResult = cityService.create(testCity);

        assertTrue(actualResult);
        ArgumentCaptor<City> captorCity = ArgumentCaptor.forClass(City.class);
        verify(cityDAO, times(1)).create(captorCity.capture());
    }

    @Test
    public void updateTest(){
        when(cityService.update(any(City.class))).thenReturn(true);

        Boolean actualResult = cityService.update(testCity);

        assertTrue(actualResult);
        ArgumentCaptor<City> captorCity = ArgumentCaptor.forClass(City.class);
        verify(cityDAO, times(1)).update(captorCity.capture());
    }
}
