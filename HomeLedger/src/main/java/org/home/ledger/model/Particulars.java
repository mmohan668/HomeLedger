package org.home.ledger.model;

import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity(name = "particulars")
@Table
public class Particulars {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "particular_id")
	private int particularId;
	@Column(name = "particular_date")
	@JsonFormat(shape = Shape.STRING,pattern = "MM/dd/yyyy")
	@Temporal(TemporalType.DATE)
	private Date particularDate;
	@Column(name = "particular_category")
	private String ParticularCategory;
	@Column(name = "particular_type")
	private String particularType;
	@Column(name = "particular_description")
	private String particularDescription;
	@Column(name = "particular_amount")
	private double particularAmount;
	@Column(name = "particular_remarks")
	private String particularRemarks;
	
	public Particulars() {
		
	}

	public Particulars(Date particularDate, String particularCategory, String particularType,
			String particularDescription, double particularAmount, String particularRemarks) {
		
		this.particularDate = particularDate;
		ParticularCategory = particularCategory;
		this.particularType = particularType;
		this.particularDescription = particularDescription;
		this.particularAmount = particularAmount;
		this.particularRemarks = particularRemarks;
	}

	public int getParticularId() {
		return particularId;
	}

	public void setParticularId(int particularId) {
		this.particularId = particularId;
	}
	public Date getParticularDate() {
		return particularDate;
	}
	
	public void setParticularDate(Date particularDate) {
		this.particularDate = particularDate;
	}

	public String getParticularCategory() {
		return ParticularCategory;
	}

	public void setParticularCategory(String particularCategory) {
		ParticularCategory = particularCategory;
	}

	public String getParticularType() {
		return particularType;
	}

	public void setParticularType(String particularType) {
		this.particularType = particularType;
	}

	public String getParticularDescription() {
		return particularDescription;
	}

	public void setParticularDescription(String particularDescription) {
		this.particularDescription = particularDescription;
	}

	public double getParticularAmount() {
		return particularAmount;
	}

	public void setParticularAmount(double particularAmount) {
		this.particularAmount = particularAmount;
	}

	public String getParticularRemarks() {
		return particularRemarks;
	}

	public void setParticularRemarks(String particularRemarks) {
		this.particularRemarks = particularRemarks;
	}

	@Override
	public String toString() {
		return "Particulars [particularId=" + particularId + ", particularDate=" + particularDate
				+ ", ParticularCategory=" + ParticularCategory + ", particularType=" + particularType
				+ ", particularDescription=" + particularDescription + ", particularAmount=" + particularAmount
				+ ", particularRemarks=" + particularRemarks + "]";
	}
	
	public static Comparator<Particulars> particularComparator = new Comparator<Particulars>() {

		@Override
		public int compare(Particulars p1, Particulars p2) {
			Date particularDate1 = p1.getParticularDate();
			Date particularDate2 = p2.getParticularDate();
			return particularDate2.compareTo(particularDate1);
		}
	};
}
