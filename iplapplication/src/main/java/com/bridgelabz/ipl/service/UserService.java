package com.bridgelabz.ipl.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.bridgelabz.ipl.dto.UserRegistrationForm;
import com.bridgelabz.ipl.exception.UserAlreadyExistAuthenticationException;

public interface UserService {

    public UserDetails registerNewUser(UserRegistrationForm UserRegistrationForm)throws UserAlreadyExistAuthenticationException;

}
