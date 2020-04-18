package org.home.ledger.model;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

@Entity(name="health_care_spends")
@Table
public class HealthCare {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "health_care_spend_id")
	private int healthCareSpendId;
	@Column(name = "health_care_spend_type")
	@ColumnTransformer(read = "UPPER(health_care_spend_type)")
	private String healthCareSpendType;
	
	public HealthCare() {
		
	}

	public HealthCare(int healthCareSpendId, String healthCareSpendType) {
		this.healthCareSpendId = healthCareSpendId;
		this.healthCareSpendType = healthCareSpendType;
	}

	public int getHealthCareSpendId() {
		return healthCareSpendId;
	}

	public void setHealthCareSpendId(int healthCareSpendId) {
		this.healthCareSpendId = healthCareSpendId;
	}

	public String getHealthCareSpendType() {
		return healthCareSpendType;
	}

	public void setHealthCareSpendType(String healthCareSpendType) {
		this.healthCareSpendType = healthCareSpendType;
	}

	@Override
	public String toString() {
		return "HealthCare [healthCareSpendId=" + healthCareSpendId + ", healthCareSpendType=" + healthCareSpendType
				+ "]";
	}
	
	public static Comparator<HealthCare> healthCareComparator = new Comparator<HealthCare>() {

		@Override
		public int compare(HealthCare h1, HealthCare h2) {
			String healthCareSpendType1 = h1.healthCareSpendType;
			String healthCareSpendType2 = h2.healthCareSpendType;
			return healthCareSpendType1.compareTo(healthCareSpendType2);
		}
	};
}
