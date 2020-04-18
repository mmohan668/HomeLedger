package org.home.ledger.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.home.ledger.model.BillType;
import org.home.ledger.model.DailyEssentials;
import org.home.ledger.model.HealthCare;
import org.home.ledger.model.Particulars;
import org.home.ledger.model.Shopping;
import org.home.ledger.model.Travel;
import org.home.ledger.model.Vehicle;
import org.home.ledger.model.VehicleSpend;
import org.home.ledger.service.BillTypeService;
import org.home.ledger.service.DailyEssentialsService;
import org.home.ledger.service.HealthCareService;
import org.home.ledger.service.ParticularService;
import org.home.ledger.service.ShoppingService;
import org.home.ledger.service.TravelService;
import org.home.ledger.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParticularController {
	@Autowired
	BillTypeService billTypeService;
	@Autowired
	DailyEssentialsService dailyEssentialsService;
	@Autowired
	HealthCareService healthCareService;
	@Autowired
	ParticularService particularService;
	@Autowired
	VehicleService vehicleService;
	@Autowired
	ShoppingService shoppingService;
	@Autowired
	TravelService travelService;

	@GetMapping("/addBillSpends")
	public String addBillSpends(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_BILL_SPENDS");
		List<BillType> billTypes = billTypeService.getBillTypes();
		request.setAttribute("billTypes", billTypes);
		return "home";
	}
	@GetMapping("/addDailyEssentialSpends")
	public String addDailyEssentialSpends(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_DAILY_ESSENTIAL_SPENDS");
		List<DailyEssentials> dailyEssntials = dailyEssentialsService.getDailyEssentialNames();
		request.setAttribute("dailyEssntials", dailyEssntials);
		return "home";
	}
	@GetMapping("/addHealthCareSpends")
	public String addHealthCareSpends(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_HEALTH_CARE_SPENDS");
		List<HealthCare> healthCareSpends = healthCareService.getHealthCareTypes();
		request.setAttribute("healthCareSpends", healthCareSpends);
		return "home";
	}
	@GetMapping("/addVehicleSpends")
	public String addVehicleSpends(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_VEHICLE_SPENDS");
		List<Vehicle> vehicles = vehicleService.getVehicles();
		request.setAttribute("vehicles", vehicles);
		List<VehicleSpend> vehicleSpends = vehicleService.getVehicleSpends(); 
		request.setAttribute("vehicleSpends", vehicleSpends);
		return "home";
	}
	@GetMapping("/addShoppingSpends")
	public String addShoppingSpends(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_SHOPPING_SPENDS");
		List<Shopping> shoppings = shoppingService.getShoppingTypes();
		request.setAttribute("shoppings", shoppings);
		return "home";
	}
	@GetMapping("/addTravelSpends")
	public String addTravelSpends(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_TRAVEL_SPENDS");
		List<Travel> travels = travelService.getTravelTypes();
		request.setAttribute("travels", travels);
		return "home";
	}
	@GetMapping("/addOtherSpends")
	public String addOtherSpends(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_OTHER_SPENDS");
		return "home";
	}
	@SuppressWarnings("unlikely-arg-type")
	@GetMapping("/viewAllSpends")
	public String  viewALlSpends(HttpServletRequest request) {
		request.setAttribute("MODE", "VIEW_ALL_SPENDS");
		List<Particulars> particulars = particularService.getAllSpends();
		Collections.sort(particulars,Particulars.particularComparator);
		BigDecimal totalAmount = particularService.getTotalAmount();
		if(totalAmount == null || totalAmount.equals(""))
			totalAmount=new BigDecimal(0.00);
		request.setAttribute("particulars", particulars);
		request.setAttribute("totalAmount", totalAmount);
		return "home";
	}
	@GetMapping("/deleteParticular")
	public String  deleteParticular(HttpServletRequest request, @RequestParam("particularId") int particularId) {
		particularService.deleteParticular(particularId);
		return "redirect:/viewAllSpends";
	}
	@PostMapping(value = "/addBillSpends")
	public String addParticulars(HttpServletRequest request , @ModelAttribute("Particulars") Particulars particulars) {
		request.setAttribute("MODE", "ADD_BILL_SPENDS");
		particularService.addParticular(particulars);
		List<BillType> billTypes = billTypeService.getBillTypes();
		request.setAttribute("billTypes", billTypes);
		request.setAttribute("status", "success");
		return "home";
	}
	@PostMapping("/addDailyEssentialSpends")
	public String addDailyEssentialSpend(HttpServletRequest request, @ModelAttribute("Particulars") Particulars particulars) {
		request.setAttribute("MODE", "ADD_DAILY_ESSENTIAL_SPENDS");
		particularService.addParticular(particulars);
		List<DailyEssentials> dailyEssntials = dailyEssentialsService.getDailyEssentialNames();
		request.setAttribute("dailyEssntials", dailyEssntials);
		request.setAttribute("status", "success");
		return "home";
	}
	@PostMapping("/addHealthCareSpends")
	public String addHealthCareSpend(HttpServletRequest request, @ModelAttribute("Particulars") Particulars particulars) {
		request.setAttribute("MODE", "ADD_HEALTH_CARE_SPENDS");
		particularService.addParticular(particulars);
		List<HealthCare> healthCareSpends = healthCareService.getHealthCareTypes();
		request.setAttribute("healthCareSpends", healthCareSpends);
		request.setAttribute("status", "success");
		return "home";
	}
	@PostMapping("/addVehicleSpends")
	public String addVehicleSpend(HttpServletRequest request, @ModelAttribute("Particulars") Particulars particulars) {
		particularService.addParticular(particulars);
		request.setAttribute("MODE", "ADD_VEHICLE_SPENDS");
		List<Vehicle> vehicles = vehicleService.getVehicles();
		request.setAttribute("vehicles", vehicles);
		List<VehicleSpend> vehicleSpends = vehicleService.getVehicleSpends(); 
		request.setAttribute("vehicleSpends", vehicleSpends);
		request.setAttribute("status", "success");
		return "home";
	}
	@PostMapping("/addShoppingSpends")
	public String addShoppingSpend(HttpServletRequest request, @ModelAttribute("Particulars") Particulars particulars) {
		particularService.addParticular(particulars);
		request.setAttribute("MODE", "ADD_SHOPPING_SPENDS");
		List<Shopping> shoppings = shoppingService.getShoppingTypes();
		request.setAttribute("shoppings", shoppings);
		request.setAttribute("status", "success");
		return "home";
	}
	@PostMapping("/addTravelSpends")
	public String addTravelSpend(HttpServletRequest request, @ModelAttribute("Particulars") Particulars particulars) {
		particularService.addParticular(particulars);
		request.setAttribute("MODE", "ADD_TRAVEL_SPENDS");
		List<Travel> travels = travelService.getTravelTypes();
		request.setAttribute("travels", travels);
		request.setAttribute("status", "success");
		return "home";
	}
	@PostMapping("/addOtherSpends")
	public String addOtherSpend(HttpServletRequest request, @ModelAttribute("Particulars") Particulars particulars) {
		particularService.addParticular(particulars);
		request.setAttribute("status", "success");
		request.setAttribute("MODE", "ADD_OTHER_SPENDS");
		return "home";
	}	
}
