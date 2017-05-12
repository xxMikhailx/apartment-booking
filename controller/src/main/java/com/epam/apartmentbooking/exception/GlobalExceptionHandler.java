package com.epam.apartmentbooking.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataAccessException(DataIntegrityViolationException e, HttpServletResponse response) throws IOException {
        log.error("Error in DB", e);
        response.sendError(HttpStatus.BAD_REQUEST.value());
        return new ModelAndView();
    }

    @ExceptionHandler(DataAccessException.class)
    public ModelAndView handleDataAccessException(DataAccessException e, HttpServletResponse response) throws IOException {
        log.error("Error in DB", e);
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ModelAndView();
    }

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable e, HttpServletResponse response) throws IOException {
        log.error("An exception has occurred", e);
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ModelAndView();
    }
}
