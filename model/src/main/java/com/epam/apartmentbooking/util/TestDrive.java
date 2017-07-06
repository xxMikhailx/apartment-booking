package com.epam.apartmentbooking.util;

import com.epam.apartmentbooking.config.MainConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("testdrive")
public class TestDrive {

    @Autowired
    private SessionFactory sessionFactory;

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class)) {
            TestDrive testDrive = context.getBean(TestDrive.class);
            testDrive.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Transactional
    private void run(){
        Session session = getSession();
        session.close();
    }

}
