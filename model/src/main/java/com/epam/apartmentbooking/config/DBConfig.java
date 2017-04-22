package com.epam.apartmentbooking.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySources(value = {@PropertySource("classpath:db.properties")})
public class DBConfig {

    private final Logger log = LoggerFactory.getLogger(DBConfig.class);
    private static final int INITIAL_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 200;

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        dataSource.setInitialSize(INITIAL_POOL_SIZE);
        dataSource.setMaxTotal(MAX_POOL_SIZE);
        return dataSource;
    }

    @Autowired
    @Bean
    @DependsOn("dataSource")
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
