package com.epam.apartmentbooking.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {

        public void onStartup(ServletContext container) throws ServletException {

            AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
            ctx.register(WebAppConfig.class);
            ctx.setServletContext(container);

            ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
            FilterRegistration.Dynamic filterRegistration = container.addFilter("encodingFilter", new CharacterEncodingFilter());
            filterRegistration.setInitParameter("encoding", "UTF-8");
            filterRegistration.setInitParameter("forceEncoding", "true");
            filterRegistration.addMappingForUrlPatterns(null, true, "/*");

            servlet.setLoadOnStartup(1);
            servlet.addMapping("/");
        }
}
