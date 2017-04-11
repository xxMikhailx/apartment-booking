package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.dao.impl.UserDAOImpl;
import com.epam.apartmentbooking.domain.User;
import com.epam.apartmentbooking.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
        when(userService.findEntityById(anyLong())).thenReturn(testUser);

        User actualUser = userService.findEntityById(1L);

        assertEquals(testUser, actualUser);
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(userDAO, times(1)).findEntityById(captor.capture());
        assertEquals(testUser.getId(), captor.getValue());
    }
}
