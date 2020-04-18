package org.home.ledger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.home.ledger.model.User;
import org.home.ledger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@PostMapping("/register")
	public String userRegistration(HttpServletRequest request, @ModelAttribute("User") User user) {
		request.setAttribute("MODE", "REGISTER");
		List<User> user1 = userService.checkUser(user.getEmailId(),user.getMobileNumber());
		if(user1 != null && !user1.isEmpty()) {
			System.out.println(user1);
			request.setAttribute("status", "failed");
			request.setAttribute("user", user);
			return "welcome";
		}
		else{
			request.setAttribute("MODE", "LOGIN");
			request.setAttribute("status", "register-success");
			userService.registerUser(user);
			return "welcome";
		}
	}
}
