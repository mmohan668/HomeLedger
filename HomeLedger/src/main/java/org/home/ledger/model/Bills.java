package org.home.ledger.model;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

@Entity(name = "bills")
@Table
public class Bills {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id")
	private int billId;
	@Column(name = "bill_type")
	private String billType;
	@Column(name = "bill_account")
	@ColumnTransformer(read = "UPPER(bill_account)")
	private String billAccount;
	@Column(name = "bill_amount")
	private double billAmount;
	
	public Bills() {
		
	}

	public Bills(int billId, String billType, String billAccount, double billAmount) {
		super();
		this.billId = billId;
		this.billType = billType;
		this.billAccount = billAccount;
		this.billAmount = billAmount;
	}

	public Bills(String billType, String billAccount, double billAmount) {
		super();
		this.billType = billType;
		this.billAccount = billAccount;
		this.billAmount = billAmount;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillAccount() {
		return billAccount;
	}

	public void setBillAccount(String billAccount) {
		this.billAccount = billAccount;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	@Override
	public String toString() {
		return "Bills [billId=" + billId + ", billType =" + billType + ", billAccount=" + billAccount + ", billAmount="
				+ billAmount + "]";
	}
	
	public static Comparator<Bills> billsComparator = new Comparator<Bills>() {
		
		public int compare(Bills b1, Bills b2) {
			String billType1 = b1.getBillAccount();
			String billType2 = b2.getBillAccount();
			return billType1.compareTo(billType2);
		}
	};
	
}
