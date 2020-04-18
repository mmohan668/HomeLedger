package org.home.ledger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.home.ledger.model.Travel;
import org.home.ledger.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TravelController {
	@Autowired
	TravelService travelService;
	@GetMapping("/organiseTravel")
	public String organiseTravelSpends(HttpServletRequest request) {
		request.setAttribute("MODE", "TRAVEL");
		List<Travel> travelTypes = travelService.getTravelTypes();
		request.setAttribute("travelTypes", travelTypes);
		return "home";
	}
	@GetMapping("/addTravelType")
	public String addTravelSpends(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_TRAVEL_TYPE");
		return "home";
	}
	@GetMapping("/editTravelType")
	public String editTravelSpends(HttpServletRequest request, @RequestParam("travelSpendId") int travelSpendId) {
		request.setAttribute("MODE", "EDIT_TRAVEL_TYPE");
		Travel travelType = travelService.getTravelTyp(travelSpendId);
		request.setAttribute("travelType", travelType);
		return "home";
	}
	@GetMapping("/deleteTravelType")
	public String deleteTravelSpends(HttpServletRequest request, @RequestParam("travelSpendId") int travelSpendId) {
		travelService.deleteTravelType(travelSpendId);
		return "redirect:/organiseTravel";
	}
	@PostMapping("/addTravelType")
	public String addTravelSpend(HttpServletRequest request, @ModelAttribute("Travel") Travel travelType) {
		request.setAttribute("MODE", "ADD_TRAVEL_TYPE");
		Travel tempTravelType = travelService.getTravelSpendName(travelType.getTravelSpendName());
		if(tempTravelType != null) {
			request.setAttribute("status", "failed");
			request.setAttribute("travelType", travelType);
		}
		else {
			travelService.addTravelType(travelType);
			request.setAttribute("status", "success");
		}
		return "home";
	}
	@PostMapping("/editTravelType")
	public String editTravelSpend(HttpServletRequest request, @ModelAttribute("Travel") Travel travelType) {
		travelService.addTravelType(travelType);
		return "redirect:/organiseTravel";
	}
}
