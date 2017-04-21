package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.config.TestConfig;
import com.epam.apartmentbooking.domain.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.filter.IColumnFilter;
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
    @DatabaseSetup("/user/user_data.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void findAllUsersTest() throws Exception {
        List<User> users = userDAO.findAllUsers();
        Assert.assertEquals(10, users.size());
        Assert.assertEquals("lbutler0", users.get(0).getLogin());
    }

    @Test
    @DatabaseSetup("/user/user_data.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void findEntityByIdTest() throws Exception {
        User user = userDAO.findEntityById(1L);
        Assert.assertEquals("lbutler0", user.getLogin());
    }

    @Test
    @DatabaseSetup("/user/user_data.xml")
    @ExpectedDatabase(value = "/user/user_data_change_password.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void changeUserPasswordTest() throws Exception{
        Long id = 1L;
        String password = "$2a$10$8jTNJqwX/jaJehssYn8HUO5uOhayAuVDTR5ZQrKu3EY5HYrgX1Z3W";
        userDAO.changeUserPassword(password, id);
    }

    @Test
    @DatabaseSetup("/user/user_data.xml")
    @ExpectedDatabase(value = "/user/user_data_create.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT,
            columnFilters = IgnoreDataColumnFilter.class)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void createTest() throws Exception{
        userDAO.create(createTestUser());
    }

    @Test
    @DatabaseSetup("/user/user_data.xml")
    @ExpectedDatabase(value = "/user/user_data_update.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void updateTest() throws Exception{
        userDAO.update(createTestUser());
    }

    @Test
    @DatabaseSetup("/user/user_data.xml")
    @ExpectedDatabase(value = "/user/user_data_remove.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
    public void removeTest() throws Exception {
        userDAO.remove(1L);
    }

    public static final class IgnoreDataColumnFilter implements IColumnFilter{
        @Override
        public boolean accept(String s, Column column) {
            return s.equalsIgnoreCase("us_creation_time");
        }
    }

    private User createTestUser(){
        User user = new User();
        user.setId(1L);
        user.setLogin("testLogin");
        user.setPassword("$2a$10$8jTNJqwX/jaJehssYn8HUO5uOhayAuVDTR5ZQrKu3EY5HYrgX1Z3W");
        user.setEmail("testEmail@test.com");
        user.setName("testName");
        user.setSurname("testSurname");
        user.setCreationDate(LocalDate.of(2017, Month.APRIL, 8));
        user.setRole(1);
        return user;
    }

}
