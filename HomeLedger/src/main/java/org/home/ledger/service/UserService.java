package org.home.ledger.service;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.home.ledger.model.User;
import org.home.ledger.principals.UserPrincipal;
import org.home.ledger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	public void registerUser(User user) {
		userRepository.save(user);
	}

	public List<User> checkUser(String emailId, BigInteger mobileNumber) {
		return userRepository.findByEmailIdOrMobileNumber(emailId, mobileNumber);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		Pattern mobilePattern = Pattern.compile("[0-9]{10}");
		Matcher mobileMatcher = mobilePattern.matcher(username);
		if(mobileMatcher.find() && username.equals(mobileMatcher.group())) {
			user = userRepository.findByMobileNumber(new BigInteger(username));
			if(user == null) {
				throw new UsernameNotFoundException("User not found");
			}
		}
		else {
			user = userRepository.findByEmailId(username);
			if(user == null) {
				throw new UsernameNotFoundException("User not found");
			}
		}
		return new UserPrincipal(user);
	}
}
