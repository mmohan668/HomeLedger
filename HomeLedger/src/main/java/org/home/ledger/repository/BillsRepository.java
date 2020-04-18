package org.home.ledger.repository;

import java.util.List;

import org.home.ledger.model.Bills;
import org.springframework.data.repository.CrudRepository;

public interface BillsRepository extends CrudRepository<Bills, Integer> {
	public Bills findByBillAccount(String billAccount);
	public List<Bills> findByBillType(String billType);
}
