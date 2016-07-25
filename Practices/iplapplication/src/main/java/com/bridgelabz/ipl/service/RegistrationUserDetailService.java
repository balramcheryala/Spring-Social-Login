package com.bridgelabz.ipl.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.ipl.database.dao.UserDAO;
import com.bridgelabz.ipl.database.model.Role;
import com.bridgelabz.ipl.database.model.User;
import com.bridgelabz.ipl.dto.LocalUser;
import com.bridgelabz.ipl.dto.UserRegistrationForm;
import com.bridgelabz.ipl.exception.UserAlreadyExistAuthenticationException;

@Service("registrationUserDetailService")
public class RegistrationUserDetailService implements UserService {

    @Autowired
    @Qualifier(value = "localUserDetailService")
    private UserDetailsService userDetailService;

    @Autowired
    private UserDAO userDAO;


    @Override
    @Transactional(value = "transactionManager")
    public LocalUser registerNewUser(final UserRegistrationForm userRegistrationForm) throws UserAlreadyExistAuthenticationException {

        com.bridgelabz.ipl.database.model.User userExist = userDAO.get(userRegistrationForm.getUserId());
        if (userExist != null) {
            throw new UserAlreadyExistAuthenticationException("User with email id " + userRegistrationForm.getEmail() + " already exist");
        }

        com.bridgelabz.ipl.database.model.User user = buildUser(userRegistrationForm);
        userDAO.save(user);
        userDAO.flush();

        return (LocalUser) userDetailService.loadUserByUsername(userRegistrationForm.getUserId());
    }

    private User buildUser(final UserRegistrationForm formDTO) {
        User user = new User();
        user.setUserId(formDTO.getUserId());
        user.setEmailId(formDTO.getEmail());
        user.setName(formDTO.getFirstName());
        user.setPassword(formDTO.getPassword());
        final HashSet<Role> roles = new HashSet<Role>();
        Role role = new Role();
        role.setName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        user.setActive(1);
        user.setProvider(formDTO.getSocialProvider().name());
        return user;
    }
}
