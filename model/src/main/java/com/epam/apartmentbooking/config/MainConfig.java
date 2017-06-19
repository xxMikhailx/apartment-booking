package com.epam.apartmentbooking.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.epam.apartmentbooking.domain",
        "com.epam.apartmentbooking.service",
        "com.epam.apartmentbooking.util"})
@Import({DBConfig.class, EmailConfig.class})
public class MainConfig {

}
