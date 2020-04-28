package org.home.ledger.service;

import java.util.Random;

import org.home.ledger.model.OTPNumber;
import org.home.ledger.model.User;
import org.home.ledger.repository.OTPRepository;
import org.home.ledger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	JavaMailSender javaMailSender;
	
	@Autowired
	private OTPRepository oTPRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	public NotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendNotificaion(User user) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmailId());
		mail.setSubject("Registration Success");
		String message = "Hi "+ user.getFirstName()+" "+user.getLastName()+" !"+"\n\n"+"Welcome to home ledger. your registration is successfull.";
		message +="\n\n This is autogenerated mail. Please don't reply for this mail.";
		message +="\n\n Thanks & Regards, \n Home Ledger Team.";
		mail.setText(message);
		
		javaMailSender.send(mail);
		
	}
	
	public boolean sendOTP(String email) {
		
		User user = userRepository.findByEmailId(email);
		if(user != null) {
			Random random = new  Random();
			int num = random.nextInt(100000);
			String otp = String.format("%05d", num);
			OTPNumber otpNumber = new OTPNumber(email, otp);
			oTPRepository.save(otpNumber);
			
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(email);
			mail.setSubject("ONE TIME PASSWORD for Password Reset");
			
			String message = "Hi "+ user.getFirstName()+" "+user.getLastName()+" !"+"\n\n"+"Here is the one time Password for your password reset";
			message +="\n\n " + otp;
			message +="\n\n " + "Please enter this OTP to reset your home ledger account password";
			message +="\n\n Thanks & Regards, \n Home Ledger Team.";
			mail.setText(message);
			
			javaMailSender.send(mail);
			
			return true;
		}
		else {
			return false;
		}
	}
}