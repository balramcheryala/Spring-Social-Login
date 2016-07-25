package com.bridgelabz.dao;
import com.bridgelabz.model.*;

public interface LoginDAO{    
      public boolean checkLogin(String userName, String userPassword);

	public void addEmployee(UsersModel user);
	public boolean checkEmail(String email);
	public boolean checkuserName(String userName);
}