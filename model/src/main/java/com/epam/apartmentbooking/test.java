package com.epam.apartmentbooking;

import com.epam.apartmentbooking.config.MainConfig;
import com.epam.apartmentbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class test {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    public static void main(String[] args) {
        /*for (int i = 1; i<=10; i++) {
            System.out.println(RandomStringUtils.random(12,true,true));
        }*/

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MainConfig.class);
        ctx.refresh();
        test test1 = (test) ctx.getBean("getTest");
        test1.restore();
        ctx.close();
    }

    private void restore(){
        userService.restoreForgottenPassword("misha.kazuchits@gmail.com");
    }

}
