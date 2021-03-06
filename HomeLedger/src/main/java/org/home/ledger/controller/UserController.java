package org.home.ledger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.home.ledger.email.validate.ValidateEmail;
import org.home.ledger.model.User;
import org.home.ledger.service.NotificationService;
import org.home.ledger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@PostMapping("/register")
	public String userRegistration(HttpServletRequest request, @ModelAttribute("User") User user) {
		request.setAttribute("MODE", "REGISTER");
		List<User> user1 = userService.checkUser(user.getEmailId(),user.getMobileNumber());
		if(user1 != null && !user1.isEmpty()) {
			request.setAttribute("status", "failed");
			request.setAttribute("user", user);
			return "welcome";
		}
		else{
			if(!ValidateEmail.isAddressValid(user.getEmailId())) {
				request.setAttribute("MODE", "REGISTER");
				request.setAttribute("status", "invalid-email");
				request.setAttribute("user", user);
				return "welcome";
			}
			else {
				request.setAttribute("MODE", "LOGIN");
				request.setAttribute("status", "register-success");
				String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
				user.setPassword(encodedPassword);
				userService.registerUser(user);
				notificationService.sendNotificaion(user);
				return "welcome";
			}
		}
	}
}
