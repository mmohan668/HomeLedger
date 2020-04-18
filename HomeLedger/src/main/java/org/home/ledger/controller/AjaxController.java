package org.home.ledger.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.home.ledger.model.Bills;
import org.home.ledger.service.BillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
	@Autowired
	BillsService billsService;
	@PostMapping("/getBillAccounts")
	public @ResponseBody List<Bills> getBillAccounts(HttpServletResponse response,@RequestParam("billType") String billType) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");		
		List<Bills> bills = billsService.getBillsByBillType(billType);
		return bills;
	}
	@PostMapping("/getBillAmount")
	public @ResponseBody double getBillAmount(HttpServletResponse response,@RequestParam("billDesc") String billAccount) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");		
		Bills bill = billsService.getBillsByBillAccount(billAccount);
		return bill.getBillAmount();
	}
}
