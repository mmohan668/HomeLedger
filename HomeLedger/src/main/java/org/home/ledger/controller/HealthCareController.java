package org.home.ledger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.home.ledger.model.HealthCare;
import org.home.ledger.service.HealthCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HealthCareController {
	@Autowired
	HealthCareService healthCareService;
	@GetMapping("/organiseHealthCare")
	public String organiseHealthCare(HttpServletRequest request) {
		request.setAttribute("MODE", "HEALTH_CARE");
		List<HealthCare> healthCareSpendTypes = healthCareService.getHealthCareTypes();
		request.setAttribute("healthCareTypes", healthCareSpendTypes);
		return "home";
	}
	@GetMapping("/addHealthCareSpendType")
	public String addHealthCareSpendTypes(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_HEALTH_CARE_TYPE");
		return "home";
	}
	@PostMapping("/addHealthCareSpendType")
	public String addHealthCareSpendType(HttpServletRequest request, @ModelAttribute("HealthCare") HealthCare healthCare) {
		request.setAttribute("MODE", "ADD_HEALTH_CARE_TYPE");
		HealthCare tempHealthCare = healthCareService.getHealthCareType(healthCare.getHealthCareSpendType());
		if(tempHealthCare != null) {
			request.setAttribute("status", "failed");
			request.setAttribute("healthCare", healthCare);
		}
		else {
			request.setAttribute("status", "success");
			healthCareService.addHealthCareType(healthCare);
		}
		return "home";
	}
	@GetMapping("/editHealthCareSpendType")
	public String editHealthCareSpendTypes(HttpServletRequest request,@RequestParam("healthCareSpendId") int healthCareSpendId) {
		request.setAttribute("MODE", "EDIT_HEALTH_CARE_TYPE");
		HealthCare healthCare = healthCareService.getHealthCareType(healthCareSpendId);
		request.setAttribute("healthCare", healthCare);
		return "home";
	}
	@PostMapping("/editHealthCareSpendType")
	public String editHealthCareSpendType(HttpServletRequest request, @ModelAttribute("HealthCare") HealthCare healthCare) {
		healthCareService.addHealthCareType(healthCare);
		return "redirect:/organiseHealthCare";
	}
	@GetMapping("/deleteHealthCareSpendType")
	public String deleteHealthCareSpendTypes(HttpServletRequest request,@RequestParam("healthCareSpendId") int healthCareSpendId) {
		healthCareService.deleteHealthCareSpendType(healthCareSpendId);
		return "redirect:/organiseHealthCare";
	}
}
