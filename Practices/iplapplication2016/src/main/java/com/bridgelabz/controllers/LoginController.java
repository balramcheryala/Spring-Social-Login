package com.bridgelabz.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import javax.validation.Valid;
import com.bridgelabz.form.LoginForm;
import com.bridgelabz.service.*;

@Controller
@RequestMapping("loginform.html")
public class LoginController {

	
	@Autowired
	public LoginService loginService;

	@RequestMapping(method = RequestMethod.GET)
	public String showForm(Map<String, LoginForm> model) {
		LoginForm loginForm = new LoginForm();
		model.put("loginForm", loginForm);
		return "loginform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@Valid LoginForm loginForm, BindingResult result,
			Map<String, LoginForm> model) {

		
		if (result.hasErrors()) {
			return "loginform";
		}
		boolean userExists = loginService.checkLogin(loginForm.getUserName(),loginForm.getPassword());
		if(userExists){
			model.put("loginForm", loginForm);
			return "loginsuccess";
		}else{
			result.rejectValue("userName","invaliduser");
			return "loginform";
		}
	}

}