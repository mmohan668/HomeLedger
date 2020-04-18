package org.home.ledger.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.home.ledger.model.Bills;
import org.home.ledger.repository.BillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillsService {
	@Autowired
	BillsRepository billsRepository;

	public List<Bills> getBills() {		
		List<Bills> bills = new ArrayList<>();
		billsRepository.findAll().forEach(b -> bills.add(b));
		Collections.sort(bills, Bills.billsComparator);
		return bills;
	}

	public Bills getBillsByBillAccount(String billAccount) {
		return billsRepository.findByBillAccount(billAccount);
	}

	public void addBillAccount(Bills bills) {
		billsRepository.save(bills);
	}

	public Bills getBillsByBillId(String billId) {
		return billsRepository.findById(Integer.parseInt(billId)).get();
	}

	public void updateBillAccount(Bills bills) {
		billsRepository.save(bills);
	}

	public void deleteBill(String billId) {
		billsRepository.deleteById(Integer.parseInt(billId));
	}

	public List<Bills> getBillsByBillType(String billType) {
		List<Bills> bills = new ArrayList<>();
		billsRepository.findByBillType(billType).forEach(b -> bills.add(b));
		Collections.sort(bills, Bills.billsComparator);
		return bills;
	}
}
