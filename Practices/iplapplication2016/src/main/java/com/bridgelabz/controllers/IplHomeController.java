package com.bridgelabz.controllers;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bridgelabz.dao.IplDaoImpl;

@Controller
@EnableWebMvc
public class IplHomeController {
		
		@Autowired
		private IplDaoImpl ipldao;
		@Autowired
		public SessionFactory sessionfactory;
	
		private Facebook facebook;
	
		// FacebookPage
			@RequestMapping(value="/facebook",method=RequestMethod.GET)
			public String helloFacebook(Model model) {
			FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("248167875567179","a1699096f76c23caabf7b84d1bdb6596");
			OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
			OAuth2Parameters params = new OAuth2Parameters();
			params.setRedirectUri("iplhome");
			params.setScope("email");
			String authorizeUrl = oauthOperations.buildAuthorizeUrl(params);
			return "iplhome";
			}
	
		
		// IplHomePage
			@RequestMapping(value="/ipl.html",method = RequestMethod.GET)
			public ModelAndView iplHomepage(Model m)
			{
			return new ModelAndView ("iplhome");
			}
			
			
			
	// Error Handling
			@ExceptionHandler(ResourceNotFoundException.class)
			@ResponseStatus(HttpStatus.NOT_FOUND)
			public ModelAndView PageNotFound(Model m)
			{
				m.addAttribute(HttpStatus.NOT_FOUND);
			return new ModelAndView ("404error");
			}

		
			
	// Searching 
		@RequestMapping(value="/search", method = RequestMethod.GET)
		@ResponseBody
			public ModelAndView searchbyname(@RequestParam(value="by", required=true) String by,@RequestParam(value="q", required=true) String query) 
			{
		
			Map<String, Object> model = new HashMap<String, Object>();
			System.out.println(by);
			System.out.println(query);
			model.put("playerslist", ipldao.jsonsearch(by,query));
		
			return new ModelAndView("playerlist",model);
			}
	
	
		
		// sorting
			@RequestMapping(value="/sort", method = RequestMethod.GET)
			public ModelAndView searchforanything(@RequestParam(value="sortby", required=true) String fieldName) 
			{
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("playerlist", ipldao.jsonsorting( fieldName));
			return new ModelAndView("playerlist",model);
			}
		
		
		
		// Request Mapping For Player
			@RequestMapping(value="/players/{request}",method= RequestMethod.GET)
			public ModelAndView playersList(@PathVariable("request") String ipl) {
			System.out.println(ipl);
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("playerslist", ipldao.iplplayergenerate(ipl));
			return new ModelAndView("playerlist",model);
			}
		
		
		// Request Mapping For Team
				@RequestMapping(value="/teams/{teamrequest}",method= RequestMethod.GET)
				public ModelAndView TeamList(@PathVariable("teamrequest") String iplteam) {
				System.out.println(iplteam);
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("teamlist", ipldao.iplteamgenerate(iplteam));
				return new ModelAndView("Teams",model);
				}
		
		}
