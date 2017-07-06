package com.epam.apartmentbooking.config;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@PropertySources(value = {@PropertySource("classpath:db.properties")})
@Import({EmailConfig.class, TestConfigJdbc.class, TestConfigHibernate.class, TestConfigEclipseLink.class})
public class TestConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DatabaseConfigBean dbUnitDatabaseConfig() {
        DatabaseConfigBean dbConfig = new DatabaseConfigBean();
        dbConfig.setDatatypeFactory(new OracleDataTypeFactory());
        return dbConfig;
    }

    @Bean
    @Autowired
    public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection(DataSource testDataSource) {
        DatabaseDataSourceConnectionFactoryBean dbConnFactoryBean = new DatabaseDataSourceConnectionFactoryBean();
        dbConnFactoryBean.setDatabaseConfig(dbUnitDatabaseConfig());
        dbConnFactoryBean.setDataSource(testDataSource);
        dbConnFactoryBean.setSchema(environment.getRequiredProperty("jdbc.testSchema"));
        return dbConnFactoryBean;
    }

}
