package org.home.ledger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "otp_numbers")
@Table
public class OTPNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "otp_id")
	private int otpId;
	@Column(name = "otp_mail_id")
	private String emailId;
	@Column(name = "otp_number")
	private String otpNumber;
	
	public OTPNumber() {
		
	}

	public OTPNumber(int otpId, String emailId, String otpNumber) {
		this.otpId = otpId;
		this.emailId = emailId;
		this.otpNumber = otpNumber;
	}
	
	public OTPNumber(String emailId, String otpNumber) {
		this.emailId = emailId;
		this.otpNumber = otpNumber;
	}

	public int getOtpId() {
		return otpId;
	}

	public void setOtpId(int otpId) {
		this.otpId = otpId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOtpNumber() {
		return otpNumber;
	}

	public void setOtpNumber(String otpNumber) {
		this.otpNumber = otpNumber;
	}

	@Override
	public String toString() {
		return "OTPNumber [otpId=" + otpId + ", emailId=" + emailId + ", otpNumber=" + otpNumber + "]";
	}
	
}
