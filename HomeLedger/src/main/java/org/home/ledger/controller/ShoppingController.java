package org.home.ledger.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.home.ledger.model.Shopping;
import org.home.ledger.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingController {
	@Autowired
	ShoppingService shoppingService;
	
	@GetMapping("/organiseShopping")
	public String orgShopping(HttpServletRequest request) {
		request.setAttribute("MODE", "SHOPPING");
		List<Shopping> shoppingTypes = shoppingService.getShoppingTypes();
		Collections.sort(shoppingTypes, Shopping.shoppingComparator);
		request.setAttribute("shoppingTypes", shoppingTypes);
		return "home";
	}
	@PostMapping("/addShoppingType")
	public String addShoppingTypes(HttpServletRequest request, @ModelAttribute("Shopping") Shopping shopping) {
		request.setAttribute("MODE", "ADD_SHOPPING_TYPE");
		Shopping tempShopping = shoppingService.getShoppingType(shopping.getShoppingTypeName());
		if(tempShopping != null) {
			request.setAttribute("status", "failed");
			request.setAttribute("shoppingType", shopping);
		}
		else {
			request.setAttribute("status", "success");
			shoppingService.addShopping(shopping);
		}
		return "home";
	}
	@GetMapping("/addShoppingType")
	public String addShoppingType(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_SHOPPING_TYPE");
		return "home";
	}
	@GetMapping("/editShoppingType")
	public String editShoppingType(HttpServletRequest request,@RequestParam("shoppingTypeId") int shoppingTypeId) {
		request.setAttribute("MODE", "EDIT_SHOPPING_TYPE");
		Shopping shopping = shoppingService.getShopping(shoppingTypeId);
		request.setAttribute("shoppingType", shopping);
		return "home";
	}
	@PostMapping("/editShoppingType")
	public String editShoppingTypes(HttpServletRequest request, @ModelAttribute("Shopping") Shopping shopping) {
		shoppingService.addShopping(shopping);
		return "redirect:/organiseShopping";
	}
	@GetMapping("/deleteShoppingType")
	public String deleteShoppingType(HttpServletRequest request,@RequestParam("shoppingTypeId") int shoppingTypeId) {
		shoppingService.deleteShopping(shoppingTypeId);
		return "redirect:/organiseShopping";
	}
}
