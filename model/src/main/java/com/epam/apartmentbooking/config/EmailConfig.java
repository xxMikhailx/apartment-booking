package com.epam.apartmentbooking.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySources(value = {@PropertySource("classpath:email.properties")})
public class EmailConfig {

    private final Logger log = LoggerFactory.getLogger(EmailConfig.class);
    private static final String MAIL_HOST = "email.host";
    private static final String MAIL_USERNAME = "email.username";
    private static final String MAIL_PASSWORD = "email.password";
    private static final String MAIL_PORT = "email.port";
    private static final String MAIL_PROTOCOL = "mail.transport.protocol";
    private static final String MAIL_AUTH = "mail.smtp.auth";
    private static final String MAIL_TLS = "mail.smtp.starttls.enable";
    private static final String MAIL_DEBUG = "mail.debug";
    private static final String MAIL_TEMPLATE_RESTORE_FORGOTTEN_PASSWORD = "mail.template.restore.password";

    @Autowired
    private Environment environment;

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(environment.getRequiredProperty(MAIL_HOST));
        javaMailSender.setUsername(environment.getRequiredProperty(MAIL_USERNAME));
        javaMailSender.setPassword(environment.getRequiredProperty(MAIL_PASSWORD));
        javaMailSender.setPort(Integer.parseInt(environment.getRequiredProperty(MAIL_PORT)));
        Properties props = javaMailSender.getJavaMailProperties();
        props.put(MAIL_PROTOCOL, environment.getRequiredProperty(MAIL_PROTOCOL));
        props.put(MAIL_AUTH, environment.getRequiredProperty(MAIL_AUTH));
        props.put(MAIL_TLS, environment.getRequiredProperty(MAIL_TLS));
        props.put(MAIL_DEBUG, environment.getRequiredProperty(MAIL_DEBUG));
        return javaMailSender;
    }

    @Bean
    public SimpleMailMessage templateRestoreForgottenPasswordMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(environment.getRequiredProperty(MAIL_TEMPLATE_RESTORE_FORGOTTEN_PASSWORD));
        return message;
    }
}
