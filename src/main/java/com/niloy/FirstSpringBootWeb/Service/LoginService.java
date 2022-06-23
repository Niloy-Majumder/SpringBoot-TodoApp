package com.niloy.FirstSpringBootWeb.Service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
	
	public boolean isUserValid(String user,String password) {
		return user.equalsIgnoreCase("Niloy") && password.equalsIgnoreCase("Dummy");
	}
}
