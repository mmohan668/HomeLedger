package org.home.ledger.model;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnTransformer;

@Entity(name = "daily_essentials")
public class DailyEssentials {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "daily_essentials_id")
	private int dailyEssentialsId;
	@Column(name = "daily_essentials_name")
	@ColumnTransformer(read = "UPPER(daily_essentials_name)")
	private String dailyEssentialsName;
	
	public DailyEssentials() {
		
	}

	public DailyEssentials(String dailyEssentialsName) {
		super();
		this.dailyEssentialsName = dailyEssentialsName;
	}

	public int getDailyEssentialsId() {
		return dailyEssentialsId;
	}

	public void setDailyEssentialsId(int dailyEssentialsId) {
		this.dailyEssentialsId = dailyEssentialsId;
	}

	public String getDailyEssentialsName() {
		return dailyEssentialsName;
	}

	public void setDailyEssentialsName(String dailyEssentialsName) {
		this.dailyEssentialsName = dailyEssentialsName;
	}

	@Override
	public String toString() {
		return "DailyEssentials [dailyEssentialsId=" + dailyEssentialsId + ", dailyEssentialsName="
				+ dailyEssentialsName + "]";
	}
	
	public static Comparator<DailyEssentials> dailyEssentialsComparator = new Comparator<DailyEssentials>() {

		@Override
		public int compare(DailyEssentials de1, DailyEssentials de2) {
			String dailyEssentialsName1 = de1.getDailyEssentialsName();
			String dailyEssentialsName2 = de2.getDailyEssentialsName();
			return dailyEssentialsName1.compareTo(dailyEssentialsName2);
		}
	};
}
