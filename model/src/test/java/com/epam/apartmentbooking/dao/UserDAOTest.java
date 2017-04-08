package com.epam.apartmentbooking.dao;

import com.epam.apartmentbooking.config.TestConfig;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class UserDAOTest {

        @Autowired
        @Qualifier("userDAO")
        private UserDAO userDAO;

        @Test
        @DatabaseSetup("/user_data.xml")
        @ExpectedDatabase(value = "/user_data_remove.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
        @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
        public void testRemove() throws Exception {
                userDAO.remove(1L);
        }

}
