package org.home.ledger.repository;

import org.home.ledger.model.DailyEssentials;
import org.springframework.data.repository.CrudRepository;

public interface DailyEssentialsRepository extends CrudRepository<DailyEssentials, Integer> {
	public DailyEssentials findByDailyEssentialsName(String dailyEssentialsName);
}
