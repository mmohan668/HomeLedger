package org.home.ledger.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.home.ledger.model.Particulars;
import org.home.ledger.repository.ParticularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticularService {
	@Autowired
	ParticularRepository particularRepository;
	
	public void addParticular(Particulars particulars) {
		particularRepository.save(particulars);
	}

	public List<Particulars> getAllSpends() {
		List<Particulars> particulars = new ArrayList<Particulars>();
		particularRepository.findAll().forEach(p -> particulars.add(p));
		return particulars;
	}

	public BigDecimal getTotalAmount() {
		return particularRepository.totalAmount();
	}

	public Particulars getParticular(int particularId) {
		return particularRepository.findById(particularId).get();
	}

	public void deleteParticular(int particularId) {
		particularRepository.deleteById(particularId);		
	}
}
