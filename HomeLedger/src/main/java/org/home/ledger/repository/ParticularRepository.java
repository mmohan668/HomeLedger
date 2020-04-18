package org.home.ledger.repository;

import java.math.BigDecimal;

import org.home.ledger.model.Particulars;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ParticularRepository extends CrudRepository<Particulars, Integer> {
	@Query(value = "SELECT sum(particular_amount) FROM Particulars", nativeQuery = true)
	public BigDecimal totalAmount();
}
