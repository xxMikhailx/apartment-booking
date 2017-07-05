package com.epam.apartmentbooking.config;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.epam.apartmentbooking.domain","com.epam.apartmentbooking.dao.impl.hibernate"})
@PropertySources(value = {@PropertySource("classpath:db.properties")})
public class TestConfigEclipseLink {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.testUsername"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.testPassword"));
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
