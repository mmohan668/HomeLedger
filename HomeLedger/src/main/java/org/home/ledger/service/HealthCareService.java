package org.home.ledger.service;

import java.util.ArrayList;
import java.util.List;

import org.home.ledger.model.HealthCare;
import org.home.ledger.repository.HealthCareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthCareService {
	@Autowired
	HealthCareRepository healthCareRepository;
	public List<HealthCare> getHealthCareTypes() {
		List<HealthCare> healthCareSpendTypes = new ArrayList<HealthCare>();
		healthCareRepository.findAll().forEach(h -> healthCareSpendTypes.add(h));
		return healthCareSpendTypes;
	}
	public HealthCare getHealthCareType(String healthCareSpendType) {
		return healthCareRepository.findByHealthCareSpendType(healthCareSpendType);
	}
	public void addHealthCareType(HealthCare healthCare) {
		healthCareRepository.save(healthCare);
	}
	public HealthCare getHealthCareType(int healthCareSpendId) {
		return healthCareRepository.findById(healthCareSpendId).get();
	}
	public void deleteHealthCareSpendType(int healthCareSpendId) {
		healthCareRepository.deleteById(healthCareSpendId);		
	}

}
