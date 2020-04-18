package org.home.ledger.repository;

import org.home.ledger.model.VehicleSpend;
import org.springframework.data.repository.CrudRepository;

public interface VehicleSpendRepository extends CrudRepository<VehicleSpend, Integer> {
	public VehicleSpend findBySpendName(String spendName);
}
