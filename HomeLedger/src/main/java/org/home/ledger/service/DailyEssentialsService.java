package org.home.ledger.service;

import java.util.ArrayList;
import java.util.List;

import org.home.ledger.model.DailyEssentials;
import org.home.ledger.repository.DailyEssentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyEssentialsService {
	@Autowired
	DailyEssentialsRepository dailyEssentialsRepository;
	
	public List<DailyEssentials> getDailyEssentialNames() {
		List<DailyEssentials> dailyEssentials = new ArrayList<DailyEssentials>();
		dailyEssentialsRepository.findAll().forEach(d -> dailyEssentials.add(d));
		return dailyEssentials;
	}

	public DailyEssentials getDailyEssential(int dailyEssentialsId) {
		return dailyEssentialsRepository.findById(dailyEssentialsId).get();
	}

	public DailyEssentials checkDailyEssentialsName(String dailyEssentialsName) {
		return dailyEssentialsRepository.findByDailyEssentialsName(dailyEssentialsName);
	}

	public void addDailyEssentials(DailyEssentials dailyEssentials) {
		dailyEssentialsRepository.save(dailyEssentials);
	}

	public void deleteDailyEssentials(int dailyEssentialsId) {
		dailyEssentialsRepository.deleteById(dailyEssentialsId);
	}
}
