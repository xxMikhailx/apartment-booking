package com.epam.apartmentbooking.controller;

import com.epam.apartmentbooking.service.ApartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

@Controller
public class ApartmentController {

    private static final Logger log = LoggerFactory.getLogger(ApartmentController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    @Qualifier("apartmentService")
    private ApartmentService apartmentService;


}
