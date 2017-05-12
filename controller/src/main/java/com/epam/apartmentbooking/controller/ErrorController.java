package com.epam.apartmentbooking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class ErrorController {

    @Autowired
    private MessageSource messageSource;

    private static final Logger log = LoggerFactory.getLogger(ErrorController.class);


    @GetMapping(value = "errors")
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest, Locale locale) {

        ModelAndView errorPage = new ModelAndView("error/error");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
        log.error("Rendering error page. Error code: " + httpErrorCode);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = messageSource.getMessage("error.bad.request", null, locale);
                break;
            }
            case 401: {
                errorMsg = messageSource.getMessage("error.unauthorized", null, locale);
                break;
            }
            case 404: {
                errorMsg = messageSource.getMessage("error.not.found", null, locale);
                break;
            }
            case 500: {
                errorMsg = messageSource.getMessage("error.internal.server", null, locale);
                break;
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        errorPage.addObject("errorCode", httpErrorCode);
        return errorPage;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
