package org.home.ledger.model;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "bill_types")
@Table
public class BillType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_type_id")
	private int billTypeId;
	@Column(name = "bill_type_name")
	private String billTypeName;
	
	public BillType() {
		
	}

	public BillType(String billTypeName) {
		super();
		this.billTypeName = billTypeName;
	}

	public int getBillTypeId() {
		return billTypeId;
	}

	public void setBillTypeId(int billTypeId) {
		this.billTypeId = billTypeId;
	}

	public String getBillTypeName() {
		return billTypeName;
	}

	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}

	@Override
	public String toString() {
		return "BillType [billTypeId=" + billTypeId + ", billTypeName=" + billTypeName + "]";
	}

	public static Comparator<BillType> billTypeNameComparator = new Comparator<BillType>() {
		
		public int compare(BillType b1, BillType b2) {
			String billTypeName1 = b1.getBillTypeName().toUpperCase();
			String billTypeName2 = b2.getBillTypeName().toUpperCase();
			return billTypeName1.compareTo(billTypeName2);
		}
	};
	
}
