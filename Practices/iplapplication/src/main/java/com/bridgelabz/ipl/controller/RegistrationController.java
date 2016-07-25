package com.bridgelabz.ipl.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.ipl.dto.LocalUser;
import com.bridgelabz.ipl.dto.UserRegistrationForm;
import com.bridgelabz.ipl.exception.UserAlreadyExistAuthenticationException;
import com.bridgelabz.ipl.service.UserService;
import com.bridgelabz.ipl.util.SecurityUtil;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "User Registration Form");
        model.setViewName("registration");
        return model;
    }

    @RequestMapping(value = {"/user/register"}, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody ModelAndView registerUser(@RequestBody UserRegistrationForm registrationForm) throws UserAlreadyExistAuthenticationException {

        if (registrationForm.getUserId() == null) {
            registrationForm.setUserId(registrationForm.getUserId());
        }

        LocalUser localUser = (LocalUser) userService.registerNewUser(registrationForm);

        SecurityUtil.authenticateUser(localUser);

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring security Hello World");
        model.addObject("user", localUser.getUsername());
        model.setViewName("user");
        return model;

    }
}
