package org.home.ledger.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/home")
	public String home(HttpServletRequest request) {
		request.setAttribute("MODE", "HOME");
		return "home";
	}
}
