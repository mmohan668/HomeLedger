package org.home.ledger.repository;

import org.home.ledger.model.HealthCare;
import org.springframework.data.repository.CrudRepository;

public interface HealthCareRepository extends CrudRepository<HealthCare, Integer> {
	public HealthCare findByHealthCareSpendType(String healthCareSpendType);
}
