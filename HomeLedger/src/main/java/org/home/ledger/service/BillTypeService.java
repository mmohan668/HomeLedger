package org.home.ledger.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.home.ledger.model.BillType;
import org.home.ledger.repository.BillTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillTypeService {
	@Autowired
	BillTypeRepository billTypeRepository;

	public void addBillType(BillType billType) {
		billTypeRepository.save(billType);
	}

	public BillType getBillType(String billTypeName) {
		return billTypeRepository.findByBillTypeName(billTypeName);
	}

	public List<BillType> getBillTypes() {
		List<BillType> billTypes = new ArrayList<BillType>();
		billTypeRepository.findAll().forEach(bt -> billTypes.add(bt));
		Collections.sort(billTypes, BillType.billTypeNameComparator);
		return billTypes;
	}

	public BillType getBillType(int billTypeId) {
		return billTypeRepository.findById(billTypeId).get();
	}

	public void deleteBillType(int billTypeId) {
		billTypeRepository.deleteById(billTypeId);
	}
}
