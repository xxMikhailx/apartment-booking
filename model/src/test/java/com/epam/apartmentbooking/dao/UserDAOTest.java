package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.config.TestConfig;
import com.epam.apartmentbooking.domain.User;
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

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class UserDAOTest {
    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    @Test
    @DatabaseSetup("/testUser/user_data.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void findAllUsersTest() throws Exception {
        List<User> users = userDAO.findAllUsers();
        Assert.assertEquals(10, users.size());
        Assert.assertEquals("lbutler0", users.get(0).getLogin());
    }

    @Test
    @DatabaseSetup("/testUser/user_data.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void findEntityByIdTest() throws Exception {
        User user = userDAO.findEntityById(1L);
        Assert.assertEquals("lbutler0", user.getLogin());
    }

    @Test
    @DatabaseSetup("/testUser/user_data.xml")
    @ExpectedDatabase(value = "/testUser/user_data_change_password.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void changeUserPasswordTest() throws Exception{
        Long id = 1L;
        String password = "testPassword";
        userDAO.changeUserPassword(password, id);
    }

    @Test
    @DatabaseSetup("/testUser/user_data.xml")
    @ExpectedDatabase(value = "/testUser/user_data_create.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void createTest() throws Exception{
        userDAO.create(createTestUser());
    }

    @Test
    @DatabaseSetup("/testUser/user_data.xml")
    @ExpectedDatabase(value = "/testUser/user_data_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void updateTest() throws Exception{
        userDAO.update(createTestUser());
    }

    @Test
    @DatabaseSetup("/testUser/user_data.xml")
    @ExpectedDatabase(value = "/testUser/user_data_remove.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void removeTest() throws Exception {
        userDAO.remove(1L);
    }

    private User createTestUser(){
        User user = new User();
        user.setId(1L);
        user.setLogin("testLogin");
        user.setPassword("testPassword");
        user.setEmail("testEmail@test.com");
        user.setName("testName");
        user.setSurname("testSurname");
        user.setCreationDate(LocalDate.of(2017, Month.APRIL, 8));
        user.setRole(1);
        return user;
    }

}
