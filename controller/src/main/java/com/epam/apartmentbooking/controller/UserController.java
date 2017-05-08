package com.epam.apartmentbooking.controller;

import com.epam.apartmentbooking.domain.User;
import com.epam.apartmentbooking.dto.UserCredential;
import com.epam.apartmentbooking.dto.UserEmail;
import com.epam.apartmentbooking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userCredential", new UserCredential());
        return "user/login";
    }

    @PostMapping("/check-user")
    public String checkUser(@Valid @ModelAttribute UserCredential userCredential, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "user/login";
        }
        log.info("User: " + userCredential.getLogin());
        log.info("Password: " + userCredential.getPassword());
        User user = new User();
        user.setLogin(userCredential.getLogin());
        user.setPassword(userCredential.getPassword());
        user = userService.loginUser(user);
        if (user != null){
            model.addAttribute("user", user);
            return "redirect:/home";
        } else {
            model.addAttribute("incorrectLoginOrPasswordMessage", "Login or password is incorrect!");
            return "user/login";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/check-register")
    public String checkRegister(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "user/register";
        }
        if (userService.create(user)){
            return "redirect:/home";
        } else {
            model.addAttribute("registrationErrorMessage", "Registration error (maybe the user exists).");
            return "user/login";
        }
    }

    @GetMapping("/restore-password")
    public String restorePassword(Model model) {
        model.addAttribute("userEmail", new UserEmail());
        return "user/restore-password";
    }

    @PostMapping("/check-restore")
    public String checkRestore(@Valid @ModelAttribute UserEmail userEmail, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "user/restore-password";
        }
        if (userService.restoreForgottenPassword(userEmail.getEmail())){
            return "redirect:/user/login";
        } else {
            model.addAttribute("restorationErrorMessage", "Restoration error.");
            return "user/restore-password";
        }
    }

    @GetMapping("/home")
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
