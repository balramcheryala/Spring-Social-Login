package com.bridgelabz.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.dao.*;
import com.bridgelabz.model.UsersModel;
@Service("loginService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LoginServiceImpl implements LoginService {

	 @Autowired
	 private LoginDAO loginDAO;

	   public void setLoginDAO(LoginDAO loginDAO) {
              this.loginDAO = loginDAO;
       }
       public boolean checkLogin(String userName, String userPassword){
              System.out.println("In Service class...Check Login");
              return loginDAO.checkLogin(userName, userPassword);
       }
       @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
   		public void addEmployee(UsersModel user) {
   		loginDAO.addEmployee(user);
   	}
       public boolean checkEmail(String email)
       {
    	   return loginDAO.checkEmail(email);
       }
       public boolean checkuserName(String userName)
       {
    	   return loginDAO.checkuserName(userName);
       }
}