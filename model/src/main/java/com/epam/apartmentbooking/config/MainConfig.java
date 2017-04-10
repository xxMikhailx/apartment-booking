package com.epam.apartmentbooking.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan({"com.epam.apartmentbooking.domain","com.epam.apartmentbooking.dao"})
@Import(DBConfig.class)
public class MainConfig {

}
