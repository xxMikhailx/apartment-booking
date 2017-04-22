package com.epam.apartmentbooking.service;

import com.epam.apartmentbooking.dao.impl.UserDAOImpl;
import com.epam.apartmentbooking.domain.User;
import com.epam.apartmentbooking.service.impl.UserServiceImpl;
import com.epam.apartmentbooking.util.MailUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserDAOImpl userDAO;

    @Spy
    private MailUtil mailUtil;

    @Spy
    private SimpleMailMessage template;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;
    private List<User> testUsers;

    @Before
    public void doBefore(){
        testUser = new User();
        testUser.setId(1L);
        testUser.setLogin("testLogin");
        testUser.setPassword("$2a$10$8jTNJqwX/jaJehssYn8HUO5uOhayAuVDTR5ZQrKu3EY5HYrgX1Z3W");
        testUser.setEmail("testEmail@test.com");
        testUser.setName("testName");
        testUser.setSurname("testSurname");
        testUser.setCreationDate(LocalDate.of(2017, Month.APRIL, 8));
        testUser.setRole(1);

        User testUser2 = new User();
        testUser2.setId(2L);
        testUser2.setLogin("testLogin2");
        testUser2.setPassword("$2a$10$8jTNJqwX/jaJehssYn8HUO5uOhayAuVDTR5ZQrKu3EY5HYrgX1Z3W");
        testUser2.setEmail("testEmail2@test.com");
        testUser2.setName("testName2");
        testUser2.setSurname("testSurname2");
        testUser2.setCreationDate(LocalDate.of(2017, Month.APRIL, 10));
        testUser2.setRole(0);

        testUsers = new ArrayList<>();
        testUsers.add(testUser);
        testUsers.add(testUser2);
    }

    @Test
    public void findAllUsersTest(){
        when(userService.findAllUsers()).thenReturn(testUsers);

        List<User> actualUsers = userService.findAllUsers();

        assertEquals(testUsers, actualUsers);
        verify(userDAO, times(1)).findAllUsers();
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

    @Test()
    public void restoreForgottenPasswordTest() {
        when(template.getText()).thenReturn("New password: %s");
        doNothing().when(mailUtil).sendSimpleMessage(anyString(),anyString(),anyString());

        Boolean actualResult = userService.restoreForgottenPassword("tmccoy5@upenn.edu");

        assertTrue(actualResult);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(userDAO, times(1)).findUserIdByEmail(captor.capture());
        verify(userDAO, times(1)).changeUserPassword(anyString(),anyLong());
    }

    @Test
    public void changePasswordTest() {
        when(userService.changeUserPassword(anyString(), anyLong())).thenReturn(true);

        Boolean actualResult = userService.changeUserPassword("testPasswordNew", 1L);

        assertTrue(actualResult);
        ArgumentCaptor<String> captorPassword = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> captorId = ArgumentCaptor.forClass(Long.class);
        verify(userDAO, times(1)).changeUserPassword(captorPassword.capture(), captorId.capture());
    }

    @Test
    public void removeTest(){
        when(userService.remove(anyLong())).thenReturn(true);

        Boolean actualResult = userService.remove(1L);

        assertTrue(actualResult);
        ArgumentCaptor<Long> captorId = ArgumentCaptor.forClass(Long.class);
        verify(userDAO, times(1)).remove(captorId.capture());
    }

    @Test
    public void createTest(){
        when(userService.create(testUser)).thenReturn(true);

        Boolean actualResult = userService.create(testUser);

        assertTrue(actualResult);
        ArgumentCaptor<User> captorUser = ArgumentCaptor.forClass(User.class);
        verify(userDAO, times(1)).create(captorUser.capture());
    }

    @Test
    public void updateTest(){
        when(userService.update(testUser)).thenReturn(true);

        Boolean actualResult = userService.update(testUser);

        assertTrue(actualResult);
        ArgumentCaptor<User> captorUser = ArgumentCaptor.forClass(User.class);
        verify(userDAO, times(1)).update(captorUser.capture());
    }
}
