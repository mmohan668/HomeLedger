package org.home.ledger.model;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

@Entity(name="vehicle_spends")
@Table
public class VehicleSpend {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spend_id")
	private int spendId;
	@Column(name = "spend_name")
	@ColumnTransformer(read = "UPPER(spend_name)")
	private String spendName;
	
	public VehicleSpend() {
		
	}

	public VehicleSpend(String spendName) {
		super();
		this.spendName = spendName;
	}
	
	public int getSpendId() {
		return spendId;
	}

	public void setSpendId(int spendId) {
		this.spendId = spendId;
	}

	public String getSpendName() {
		return spendName;
	}

	public void setSpendName(String spendName) {
		this.spendName = spendName;
	}

	public static Comparator<VehicleSpend> getVehicleSpendComparator() {
		return vehicleSpendComparator;
	}

	public static void setVehicleSpendComparator(Comparator<VehicleSpend> vehicleSpendComparator) {
		VehicleSpend.vehicleSpendComparator = vehicleSpendComparator;
	}

	@Override
	public String toString() {
		return "VehicleSpend [spendId=" + spendId + ", spendName=" + spendName + "]";
	}
	
	public static Comparator<VehicleSpend> vehicleSpendComparator = new Comparator<VehicleSpend>() {

		@Override
		public int compare(VehicleSpend vs1, VehicleSpend vs2) {
			String spendName1 = vs1.getSpendName();
			String spendName2 = vs2.getSpendName();
			return spendName1.compareTo(spendName2);
		}
		
	};
}
