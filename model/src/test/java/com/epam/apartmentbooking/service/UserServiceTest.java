package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.dao.impl.UserDAOImpl;
import com.epam.apartmentbooking.domain.User;
import com.epam.apartmentbooking.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserDAOImpl userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    @Before
    public void doBefore(){
        testUser = new User();
        testUser.setId(1L);
        testUser.setLogin("testLogin");
        testUser.setPassword("testPassword");
        testUser.setEmail("testEmail@test.com");
        testUser.setName("testName");
        testUser.setSurname("testSurname");
        testUser.setCreationDate(LocalDate.of(2017, Month.APRIL, 8));
        testUser.setRole(1);
    }

    @Test
    public void findEntityByIdTest(){
        when(userService.findEntityById(1L)).thenReturn(testUser);
        Assert.assertEquals(userService.findEntityById(1L), testUser);
    }
}
