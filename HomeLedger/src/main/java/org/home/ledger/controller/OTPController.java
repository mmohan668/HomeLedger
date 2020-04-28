package org.home.ledger.controller;

import javax.servlet.http.HttpServletRequest;

import org.home.ledger.model.OTPNumber;
import org.home.ledger.model.User;
import org.home.ledger.repository.OTPRepository;
import org.home.ledger.repository.UserRepository;
import org.home.ledger.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OTPController {
	
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private OTPRepository otpRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@GetMapping("/forgotPassword")
	public String forgotPassword(HttpServletRequest request) {
		request.setAttribute("MODE", "FORGOTPASSWORD");
		return "welcome";
	}
	
	@PostMapping("/getOtp")
	public String getOTP(HttpServletRequest request, @ModelAttribute("OTPNumber") OTPNumber otpNumber) {
		
		if(notificationService.sendOTP(otpNumber.getEmailId())) {
			request.setAttribute("MODE", "OTPNUMBER");
			request.setAttribute("OTPNumber", otpNumber);
			return "welcome";
		}
		else {
			return "redirect:/forgotPassword";
		}
	}
	
	@GetMapping("/resetPassword")
	public String resetPassword(HttpServletRequest request, @ModelAttribute("OTPNumber") OTPNumber otpNumber) {
		
		OTPNumber otp = otpRepository.findByEmailIdAndOtpNumber(otpNumber.getEmailId(), otpNumber.getOtpNumber());
		if(otp != null) {
			User user = userRepository.findByEmailId(otpNumber.getEmailId());
			request.setAttribute("user", user);
			request.setAttribute("MODE", "RESETPASSWORD");
			otpRepository.deleteById(otp.getOtpId());
		}
		else {
			request.setAttribute("otpNumber", otpNumber);
			request.setAttribute("MODE", "OTPNUMBER");
			request.setAttribute("status", "failed");
		}
		return "welcome";
	}
	
	@PostMapping("/resetPassword")
	public String resetUser(HttpServletRequest request, @ModelAttribute("User") User user) {
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		request.setAttribute("MODE", "RESETSUCCESS");
		return "welcome";
	}
}
 