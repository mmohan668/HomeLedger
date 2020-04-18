package org.home.ledger.repository;

import org.home.ledger.model.BillType;
import org.springframework.data.repository.CrudRepository;

public interface BillTypeRepository extends CrudRepository<BillType, Integer> {
	public BillType findByBillTypeName(String billTypeName);
}
