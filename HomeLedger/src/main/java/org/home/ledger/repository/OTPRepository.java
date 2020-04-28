package org.home.ledger.repository;

import org.home.ledger.model.OTPNumber;
import org.springframework.data.repository.CrudRepository;

public interface OTPRepository extends CrudRepository<OTPNumber, Integer> {
	OTPNumber findByEmailIdAndOtpNumber(String email, String otpNumber);
}
