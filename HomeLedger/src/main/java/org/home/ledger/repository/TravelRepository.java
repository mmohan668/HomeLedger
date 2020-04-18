package org.home.ledger.repository;

import org.home.ledger.model.Travel;
import org.springframework.data.repository.CrudRepository;

public interface TravelRepository extends CrudRepository<Travel, Integer> {
	public Travel findByTravelSpendName(String travelSpendName);
}
