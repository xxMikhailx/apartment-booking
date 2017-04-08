package com.epam.apartmentbooking.config;

import com.epam.apartmentbooking.TestDrive;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan({"com.epam.apartmentbooking.domain","com.epam.apartmentbooking.dao"})
@Import(DBConfig.class)
public class MainConfig {

    @Bean
    public TestDrive testDrive(){
        return new TestDrive();
    }

}
