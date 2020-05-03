package org.home.ledger.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.home.ledger.model.Particulars;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ParticularRepository extends CrudRepository<Particulars, Integer> {
	@Query(value = "SELECT sum(particular_amount) FROM Particulars", nativeQuery = true)
	public BigDecimal totalAmount();
	@Query(value = "SELECT sum(particular_amount) FROM Particulars where particular_date >= :from and particular_date <= :thru", nativeQuery = true)
	public BigDecimal totalAmountbyPeriod(@Param("from") Date from, @Param("thru") Date thru);
	public List<Particulars> findByParticularDateBetween(Date from, Date thru);
	@Query(value = "SELECT particular_date from particulars", nativeQuery = true)
	public List<Date> findDates();
}
