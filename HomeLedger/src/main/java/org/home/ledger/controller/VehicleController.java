package org.home.ledger.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.home.ledger.model.Vehicle;
import org.home.ledger.model.VehicleSpend;
import org.home.ledger.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VehicleController {
	@Autowired
	VehicleService vehicleService;
	
	@GetMapping("/organiseVehicles")
	public String vehicle(HttpServletRequest request) {
		request.setAttribute("MODE", "VEHICLE");
		List<Vehicle> vehicles = vehicleService.getVehicles();
		Collections.sort(vehicles,Vehicle.vehicleCoparator);
		request.setAttribute("vehicles", vehicles);
		List<VehicleSpend> vehicleSpends = vehicleService.getVehicleSpends();
		Collections.sort(vehicleSpends,VehicleSpend.vehicleSpendComparator);
		request.setAttribute("vehicleSpends", vehicleSpends);
		return "home";
	}
	
	@GetMapping("/addVehicle")
	public String addVehicle(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_VEHICLE");
		return "home";
	}
	@GetMapping("/addVehicleSpend")
	public String addVehicleSpend(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_VEHICLE_SPEND");
		return "home";
	}
	@GetMapping("/editVehicle")
	public String editVehicle(HttpServletRequest request,@RequestParam("vehicleId") String vehicleId) {
		request.setAttribute("MODE", "EDIT_VEHICLE");
		Vehicle vehicle = vehicleService.getVehicle(Integer.parseInt(vehicleId));
		request.setAttribute("vehicle", vehicle);
		return "home";
	}
	@GetMapping("/editVehicleSpend")
	public String editVehicleSpend(HttpServletRequest request,@RequestParam("spendId") String spendId) {
		request.setAttribute("MODE", "EDIT_VEHICLE_SPEND");
		VehicleSpend vehicleSpend = vehicleService.getVehicleSpend(Integer.parseInt(spendId));
		request.setAttribute("vehicleSpend", vehicleSpend);
		return "home";
	}
	@GetMapping("/deleteVehicle")
	public String deleteVehicle(HttpServletRequest request,@RequestParam("vehicleId") String vehicleId) {
		vehicleService.deleteVehicle(Integer.parseInt(vehicleId));
		return "redirect:/organiseVehicles";
	}
	@GetMapping("/deleteVehicleSpend")
	public String deleteVehicleSpend(HttpServletRequest request,@RequestParam("spendId") String spendId) {
		vehicleService.deleteVehicleSpend(Integer.parseInt(spendId));
		return "redirect:/organiseVehicles";
	}
	@PostMapping("/addVehicle")
	public String addVehicles(HttpServletRequest request, @ModelAttribute("Vehicle") Vehicle vehicle) {
		request.setAttribute("MODE", "ADD_VEHICLE");
		Vehicle  vehicle1 = vehicleService.getVehicle(vehicle.getVehicleNumber());
		if(vehicle1 != null) {
			request.setAttribute("status", "failed");
			request.setAttribute("vehicle", vehicle);
		}
		else {
			vehicleService.addVehilce(vehicle);
			request.setAttribute("status", "success");
		}
		return "home";
	}
	@PostMapping("/editVehicle")
	public String editVehicle(HttpServletRequest request, @ModelAttribute("Vehicle") Vehicle vehicle) {
		vehicleService.addVehilce(vehicle);
		return "redirect:/organiseVehicles";
	}
	@PostMapping("/addVehicleSpend")
	public String addVehicleSpends(HttpServletRequest request,@ModelAttribute("VehicleSpend") VehicleSpend vehicleSpend) {
		request.setAttribute("MODE", "ADD_VEHICLE_SPEND");
		VehicleSpend  vehicleSpend1 = vehicleService.getVehicleSpend(vehicleSpend.getSpendName());
		if(vehicleSpend1 != null) {
			request.setAttribute("status", "failed");
			request.setAttribute("vehicleSpend", vehicleSpend);
		}
		else {
			vehicleService.addVehilceSpend(vehicleSpend);
			request.setAttribute("status", "success");
		}
		return "home";
	}	
	@PostMapping("/editVehicleSpend")
	public String editVehicleSpend(HttpServletRequest request,@ModelAttribute("VehicleSpend") VehicleSpend vehicleSpend) {
		vehicleService.addVehilceSpend(vehicleSpend);
		return "redirect:/organiseVehicles";
	}
}
