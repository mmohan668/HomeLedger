package org.home.ledger.repository;

import org.home.ledger.model.Shopping;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingRepository extends CrudRepository<Shopping, Integer> {
	public Shopping findByShoppingTypeName(String shoppingTypeName);
}
