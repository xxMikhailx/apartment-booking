package com.epam.apartmentbooking.config;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Profile("eclipselink")
@EnableTransactionManagement
@ComponentScan("com.epam.apartmentbooking.dao.impl.hibernate")
@PropertySource(value = { "classpath:db.properties" })
public class EclipseLinkConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    private Properties eclipseLinkProperties() {
        Properties properties = new Properties();
        properties.put(PersistenceUnitProperties.TARGET_DATABASE, environment.getRequiredProperty("eclipselink.target-database"));
        properties.put(PersistenceUnitProperties.WEAVING, environment.getRequiredProperty("eclipselink.weaving"));
        properties.put(PersistenceUnitProperties.LOGGING_LEVEL, environment.getRequiredProperty("eclipselink.logging.level"));
        return properties;
    }

    @Bean()
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(environment.getRequiredProperty("hibernate.packages.to.scan"));
        entityManagerFactory.setJpaProperties(eclipseLinkProperties());
        entityManagerFactory.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());
        return entityManagerFactory;
    }

    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
