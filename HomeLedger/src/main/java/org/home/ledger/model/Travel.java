package org.home.ledger.model;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

@Entity(name = "travel_spends")
@Table
public class Travel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "travel_spend_id")
	private int travelSpendId;
	@Column(name = "travel_spend_name")
	@ColumnTransformer(read = "UPPER(travel_spend_name)")
	private String travelSpendName;
	
	public Travel() {
		
	}

	public Travel(int travelSpendId, String travelSpendName) {
		this.travelSpendId = travelSpendId;
		this.travelSpendName = travelSpendName;
	}

	public int getTravelSpendId() {
		return travelSpendId;
	}

	public void setTravelSpendId(int travelSpendId) {
		this.travelSpendId = travelSpendId;
	}

	public String getTravelSpendName() {
		return travelSpendName;
	}

	public void setTravelSpendName(String travelSpendName) {
		this.travelSpendName = travelSpendName;
	}

	@Override
	public String toString() {
		return "Travel [travelSpendId=" + travelSpendId + ", travelSpendName=" + travelSpendName + "]";
	}
	
	public static Comparator<Travel> travelComparator = new Comparator<Travel>() {

		@Override
		public int compare(Travel t1, Travel t2) {
			String travelSpendName1 = t1.getTravelSpendName();
			String travelSpendName2 = t2.getTravelSpendName();
			return travelSpendName1.compareTo(travelSpendName2);
		}
	};
}
