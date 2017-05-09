package com.epam.apartmentbooking.controller;

import com.epam.apartmentbooking.domain.User;
import com.epam.apartmentbooking.dto.UserCredential;
import com.epam.apartmentbooking.dto.UserEmail;
import com.epam.apartmentbooking.dto.UserPassword;
import com.epam.apartmentbooking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;
import java.util.concurrent.Callable;

@Controller
@SessionAttributes("user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model, Locale locale) {
        model.addAttribute("userCredential", new UserCredential());
        return "user/login";
    }

    @RequestMapping("/check-user")
    public String checkUser(@Valid @ModelAttribute UserCredential userCredential, BindingResult bindingResult, Model model, Locale locale){
        if (bindingResult.hasErrors()){
            return "user/login";
        }
        User user = new User();
        user.setLogin(userCredential.getLogin());
        user.setPassword(userCredential.getPassword());
        user = userService.loginUser(user);
        if (user != null){
            model.addAttribute("user", user);
            return "redirect:/home";
        } else {
            model.addAttribute("incorrectLoginOrPasswordMessage",
                    messageSource.getMessage("message.incorrect.login.password", null, locale));
            return "user/login";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUser", new User());
        return "user/register";
    }

    @RequestMapping("/check-register")
    public String checkRegister(@Valid @ModelAttribute("newUser") User user, BindingResult bindingResult, Model model, Locale locale){
        if (bindingResult.hasErrors()){
            return "user/register";
        }
        if (userService.create(user)){
            model.addAttribute("user", userService.findAllUsers()
                    .parallelStream()
                    .filter(u -> user.getLogin().equals(u.getLogin()))
                    .findFirst()
                    .orElse(user));
            return "redirect:/home";
        } else {
            model.addAttribute("registrationErrorMessage",
                    messageSource.getMessage("message.registration.error", null, locale));
            return "user/login";
        }
    }

    @GetMapping("/restore-password")
    public String restorePassword(Model model) {
        model.addAttribute("userEmail", new UserEmail());
        return "user/restore-password";
    }

    @RequestMapping("/check-restore")
    public Callable<String> checkRestore(@Valid @ModelAttribute UserEmail userEmail, BindingResult bindingResult, Model model, Locale locale){
        return () -> {
            if (bindingResult.hasErrors()) {
                return "user/restore-password";
            }
            if (userService.restoreForgottenPassword(userEmail.getEmail())) {
                return "redirect:/login";
            } else {
                model.addAttribute("restorationErrorMessage",
                        messageSource.getMessage("message.restoration.error", null, locale));
                return "user/restore-password";
            }
        };
    }

    @GetMapping("/change-password")
    public ModelAndView changePassword(){
//        model.addAttribute("newPassword", new UserPassword());
        return new ModelAndView("user/change-password", "userPassword", new UserPassword());
    }

    @RequestMapping("check-change-password")
    public String checkChangePassword(@Valid @ModelAttribute UserPassword userPassword, BindingResult bindingResult, @SessionAttribute User user, Model model, Locale locale){
        if (bindingResult.hasErrors()){
            return "user/change-password";
        }
        if (userPassword.getNewPassword().equals(userPassword.getNewPasswordCopy()) && BCrypt.checkpw(userPassword.getOldPassword(), user.getPassword())) {
            if (userService.changeUserPassword(userPassword.getNewPassword(), user.getId())){
                return "redirect:/home";
            } else {
                model.addAttribute("changePasswordError",
                        messageSource.getMessage("message.change.password.server.error", null, locale));
                return "user/change-password";
            }
        } else {
            model.addAttribute("changePasswordError",
                    messageSource.getMessage("message.change.password.invalid.error", null, locale));
            return "user/change-password";
        }
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus, Model model){
        sessionStatus.setComplete();
        model.addAttribute("userCredential", new UserCredential());
        return "user/login";
    }
}
