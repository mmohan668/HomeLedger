package org.home.ledger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.home.ledger.model.BillType;
import org.home.ledger.model.Bills;
import org.home.ledger.service.BillTypeService;
import org.home.ledger.service.BillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BillsController {
	@Autowired
	BillsService billsService;
	@Autowired
	BillTypeService billTypeService;
	@GetMapping("/organiseBills")
	private String organizeBills(HttpServletRequest request) {
		request.setAttribute("MODE", "BILLS");
		List<Bills> bills = billsService.getBills();
		request.setAttribute("bills", bills);
		List<BillType> billTypes = billTypeService.getBillTypes();
		request.setAttribute("billTypes", billTypes);
		return "home";
	}
	@GetMapping("/addBillType")
	public String addBillTypes(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_BILL_TYPE");
		return "home";
	}
	@GetMapping("/addBillAccount")
	public String addBillAccount(HttpServletRequest request) {
		request.setAttribute("MODE", "ADD_BILL_ACCOUNT");
		List<BillType> billTypes = billTypeService.getBillTypes();
		request.setAttribute("billTypes", billTypes);
		return "home";
	}
	@PostMapping("/addBillType")
	public String addBillType(HttpServletRequest request,@ModelAttribute("BillType") BillType billType) {
		request.setAttribute("MODE", "ADD_BILL_TYPE");
		BillType bill = billTypeService.getBillType(billType.getBillTypeName());
		if(bill != null) {
			request.setAttribute("status", "failed");
			request.setAttribute("billType", billType);
		}
		else {
			billTypeService.addBillType(billType);
			request.setAttribute("status", "success");
		}
		return "home";
	}
	@PostMapping("/editBillType")
	public String editBillType(HttpServletRequest request,@ModelAttribute("BillType") BillType billType) {
		billTypeService.addBillType(billType);
		return "redirect:/organiseBills";
	}
	@PostMapping("/addBillAccount")
	public String addBillAccount(HttpServletRequest request,@ModelAttribute("BillAccounts") Bills bills) {
		request.setAttribute("MODE", "ADD_BILL_ACCOUNT");
		Bills bill = billsService.getBillsByBillAccount(bills.getBillAccount());
		if(bill != null) {
			request.setAttribute("status", "failed");
			request.setAttribute("bills", bills);
		}
		else {
			billsService.addBillAccount(bills);
			request.setAttribute("status", "success");
		}
		List<BillType> billTypes = billTypeService.getBillTypes();
		request.setAttribute("billTypes", billTypes);
		return "home";
	}
	@GetMapping("/editBill")
	public String editBill(HttpServletRequest request,@RequestParam("billId") String billId) {
		request.setAttribute("MODE", "EDIT_BILL_ACCOUNT");
		Bills bills = billsService.getBillsByBillId(billId);
		request.setAttribute("bills", bills);
		return "home";
	}
	@PostMapping("/editBillAccount")
	public String updateBill(HttpServletRequest request,@ModelAttribute("billAccounts") Bills bill) {
		billsService.updateBillAccount(bill);
		return "redirect:/organiseBills";
	}
	@GetMapping("/deleteBill")
	public String deleteBill(HttpServletRequest request,@RequestParam("billId") String billId) {
		billsService.deleteBill(billId);
		return "redirect:/organiseBills";
	}
	@GetMapping("/editBillType")
	public String editBillType(HttpServletRequest request,@RequestParam("billTypeId") int billTypeId) {
		request.setAttribute("MODE", "EDIT_BILL_TYPE");
		BillType billtype = billTypeService.getBillType(billTypeId);
		request.setAttribute("billtype", billtype);
		return "home";
	}
	@GetMapping("/deleteBillType")
	public String deleteBillType(HttpServletRequest request,@RequestParam("billTypeId") int billTypeId) {
		billTypeService.deleteBillType(billTypeId);
		return "redirect:/organiseBills";
	}
}
