package org.home.ledger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.home.ledger.model.DailyEssentials;
import org.home.ledger.service.DailyEssentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DailyEssentialsController {
	@Autowired
	DailyEssentialsService dailyEssentialsService;
	
	@GetMapping("/organiseEssentials")
	public String organiseEssentials(HttpServletRequest request) {
		request.setAttribute("MODE", "DAILY_ESSENTIALS");
		List<DailyEssentials> dailyEssentials = dailyEssentialsService.getDailyEssentialNames();
		request.setAttribute("dailyEssentials", dailyEssentials);
		return "home";
	}
	
	@GetMapping("/addDailyEssentials")
	public String addDailyEssential(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_DAILY_ESSENTIALS");
		return "home";
	}
	
	@GetMapping("/editDailyEssentials")
	public String editDailyEssentials(HttpServletRequest request, @RequestParam("dailyEssentialsId") int dailyEssentialsId) {
		request.setAttribute("MODE", "EDIT_DAILY_ESSENTIALS");
		DailyEssentials dailyEssentials = dailyEssentialsService.getDailyEssential(dailyEssentialsId);
		request.setAttribute("dailyEssentials", dailyEssentials);
		return "home";
	}
	
	@PostMapping("/addDailyEssentials")
	public String addDailyEssentials(HttpServletRequest request, @ModelAttribute("DailyEssentials") DailyEssentials dailyEssentials) {
		request.setAttribute("MODE", "ADD_DAILY_ESSENTIALS");
		DailyEssentials dailyEssentials1 = dailyEssentialsService.checkDailyEssentialsName(dailyEssentials.getDailyEssentialsName());
		if(dailyEssentials1 != null) {
			request.setAttribute("status", "failed");
			request.setAttribute("dailyEssentials", dailyEssentials);
		}
		else {
			request.setAttribute("status", "success");
			dailyEssentialsService.addDailyEssentials(dailyEssentials);
		}
		return "home";
	}
	
	@PostMapping("/editDailyEssentials")
	public String editDailyEssentials(HttpServletRequest request, @ModelAttribute("DailyEssentials") DailyEssentials dailyEssentials1) {
		dailyEssentialsService.addDailyEssentials(dailyEssentials1);
		return "redirect:/organiseEssentials";
	}
	
	@GetMapping("/deleteDailyEssentials")
	public String deleteDailyEssentials(HttpServletRequest request, @RequestParam("dailyEssentialsId") int dailyEssentialsId) {
		dailyEssentialsService.deleteDailyEssentials(dailyEssentialsId);
		return "redirect:/organiseEssentials";
	}
}
