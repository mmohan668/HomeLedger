package org.home.ledger.service;

import java.util.ArrayList;
import java.util.List;

import org.home.ledger.model.Travel;
import org.home.ledger.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelService {
	@Autowired
	TravelRepository travelRepository;

	public Travel getTravelSpendName(String travelSpendName) {
		return travelRepository.findByTravelSpendName(travelSpendName);
	}

	public void addTravelType(Travel travelType) {
		travelRepository.save(travelType);		
	}

	public List<Travel> getTravelTypes() {
		List<Travel> travelTypes = new ArrayList<Travel>();
		travelRepository.findAll().forEach(t -> travelTypes.add(t));
		return travelTypes;
	}

	public Travel getTravelTyp(int travelSpendId) {
		return travelRepository.findById(travelSpendId).get();
	}

	public void deleteTravelType(int travelSpendId) {
		travelRepository.deleteById(travelSpendId);
	}
}
