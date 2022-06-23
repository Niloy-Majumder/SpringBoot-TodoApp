package com.niloy.FirstSpringBootWeb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.niloy.FirstSpringBootWeb.Service.LoginService;

@Controller
@SessionAttributes("name")
public class WelcomeController {
	@Autowired
	LoginService service;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		model.put("navClassHome", "active");
		model.put("name",getLoggedInUserName(model));
		return "welcome";
	}
	
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
	
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String showWelcomePage(@RequestParam String name,String password,ModelMap model) {
//		if(!service.isUserValid(name, password)) {
//			model.put("errorMessage", "Invalid Credentials");
//			return "login";
//		}
//		model.put("name", name);
//		model.put("password", password);
//		return "welcome";
//		
//	}
}
