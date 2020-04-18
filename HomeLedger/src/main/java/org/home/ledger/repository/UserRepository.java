package org.home.ledger.repository;

import java.math.BigInteger;
import java.util.List;

import org.home.ledger.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	public User findByEmailIdAndPassword(String emailId, String password);
	public User findByMobileNumberAndPassword(BigInteger mobileNumber, String password);
	public User findByEmailId(String emailId);
	public User findByMobileNumber(BigInteger mobileNumber);
	public List<User> findByEmailIdOrMobileNumber(String emailId, BigInteger mobileNumber);
}
