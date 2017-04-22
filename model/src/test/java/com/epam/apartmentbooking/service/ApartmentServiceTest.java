package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.dao.impl.ApartmentDAOImpl;
import com.epam.apartmentbooking.domain.*;
import com.epam.apartmentbooking.service.impl.ApartmentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApartmentServiceTest {
    @Mock
    private ApartmentDAOImpl apartmentDAO;

    @InjectMocks
    private ApartmentServiceImpl apartmentService;

    private Apartment testApartment;
    private List<Apartment> testApartments;

    @Before
    public void doBefore(){
        testApartment = new Apartment();
        testApartment.setId(1L);
        testApartment.setIdOwner(1L);
        testApartment.setTitle("Sonsing");
        testApartment.setDescription("In hac habitasse platea dictumst.");
        testApartment.setApartmentType(ApartmentType.FLAT);
        testApartment.setPrice((BigDecimal.valueOf(44.3245)));
        testApartment.setMaxGuestNumber(4);
        testApartment.setBedNumber(3);
        testApartment.setApartmentStatus(ApartmentStatus.AVAILABLE);
        testApartment.setAddress("76994 Macpherson Court");
        Country country = new Country();
        country.setId(1L);
        country.setTitle("China");
        City city = new City();
        city.setId(1L);
        city.setTitle("Pinellas Park");
        city.setCountry(country);
        testApartment.setCity(city);

        Apartment testApartment2 = new Apartment();
        testApartment2.setId(2L);
        testApartment2.setIdOwner(2L);
        testApartment2.setTitle("Sonsing2");
        testApartment2.setDescription("In hac habitasse platea dictumst.2");
        testApartment2.setApartmentType(ApartmentType.ROOM);
        testApartment2.setPrice((BigDecimal.valueOf(94.3245)));
        testApartment2.setMaxGuestNumber(4);
        testApartment2.setBedNumber(3);
        testApartment2.setApartmentStatus(ApartmentStatus.AVAILABLE);
        testApartment2.setAddress("76994 Macpherson Court2");
        Country country2 = new Country();
        country2.setId(2L);
        country2.setTitle("China2");
        City city2 = new City();
        city2.setId(2L);
        city2.setTitle("Pinellas Park2");
        city2.setCountry(country2);
        testApartment2.setCity(city2);

        testApartments = new ArrayList<>();
        testApartments.add(testApartment);
        testApartments.add(testApartment2);
    }

    @Test
    public void findAllAvailableApartmentsTest(){
        when(apartmentService.findAllAvailableApartments()).thenReturn(testApartments);

        List<Apartment> actualApartments = apartmentService.findAllAvailableApartments();

        assertEquals(testApartments, actualApartments);
        verify(apartmentDAO, times(1)).findAllAvailableApartments();
    }

    @Test
    public void findAllApartmentsTest(){
        when(apartmentService.findAllApartments()).thenReturn(testApartments);

        List<Apartment> actualApartments = apartmentService.findAllApartments();

        assertEquals(testApartments, actualApartments);
        verify(apartmentDAO, times(1)).findAllApartments();
    }

    @Test
    public void findEntityByIdTest(){
        when(apartmentService.findEntityById(anyLong())).thenReturn(testApartment);

        Apartment actualApartment = apartmentService.findEntityById(1L);

        assertEquals(testApartment, actualApartment);
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(apartmentDAO, times(1)).findEntityById(captor.capture());
        assertEquals(testApartment.getId(), captor.getValue());
    }

    @Test
    public void removeTest(){
        when(apartmentService.remove(anyLong())).thenReturn(true);

        Boolean actualResult = apartmentService.remove(1L);

        assertTrue(actualResult);
        ArgumentCaptor<Long> captorId = ArgumentCaptor.forClass(Long.class);
        verify(apartmentDAO, times(1)).remove(captorId.capture());
    }

    @Test
    public void createTest(){
        when(apartmentService.create(any(Apartment.class))).thenReturn(true);

        Boolean actualResult = apartmentService.create(testApartment);

        assertTrue(actualResult);
        ArgumentCaptor<Apartment> captorApartment = ArgumentCaptor.forClass(Apartment.class);
        verify(apartmentDAO, times(1)).create(captorApartment.capture());
    }

    @Test
    public void updateTest(){
        when(apartmentService.update(any(Apartment.class))).thenReturn(true);

        Boolean actualResult = apartmentService.update(testApartment);

        assertTrue(actualResult);
        ArgumentCaptor<Apartment> captorApartment = ArgumentCaptor.forClass(Apartment.class);
        verify(apartmentDAO, times(1)).update(captorApartment.capture());
    }
}
