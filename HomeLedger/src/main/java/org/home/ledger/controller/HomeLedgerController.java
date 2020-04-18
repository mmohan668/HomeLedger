package org.home.ledger.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeLedgerController {	
	@RequestMapping("/")
	public String welcome(HttpServletRequest request) {
		request.setAttribute("MODE", "HOME");
		return "home";
	}
	@GetMapping("/welcome")
	public String welcomeLogin(HttpServletRequest request) {
		request.setAttribute("MODE", "WELCOME");
		return "welcome";
	}
	@GetMapping("/logout-success")
	public String logout(HttpServletRequest request) {
		request.setAttribute("MODE", "WELCOME");
		return "welcome";
	}
	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		request.setAttribute("MODE", "LOGIN");
		return "welcome";
	}
	@GetMapping("/register")
	public String register(HttpServletRequest request) {
		request.setAttribute("MODE", "REGISTER");
		return "welcome";
	}
}
