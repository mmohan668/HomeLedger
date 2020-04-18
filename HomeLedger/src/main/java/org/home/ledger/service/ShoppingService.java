package org.home.ledger.service;

import java.util.ArrayList;
import java.util.List;

import org.home.ledger.model.Shopping;
import org.home.ledger.repository.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingService {
	@Autowired
	ShoppingRepository shoppingRepository;
	
	public List<Shopping> getShoppingTypes() {
		List<Shopping> shoppingTypes = new ArrayList<Shopping>();
		shoppingRepository.findAll().forEach(s -> shoppingTypes.add(s));
		return shoppingTypes;
	}

	public Shopping getShoppingType(String shoppingTypeName) {
		return shoppingRepository.findByShoppingTypeName(shoppingTypeName);
	}

	public void addShopping(Shopping shopping) {
		shoppingRepository.save(shopping);
	}

	public Shopping getShopping(int shoppingTypeId) {
		return shoppingRepository.findById(shoppingTypeId).get();
	}

	public void deleteShopping(int shoppingTypeId) {
		shoppingRepository.deleteById(shoppingTypeId);;
	}

}
