package org.home.ledger.model;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

@Entity(name = "vehicle_details")
@Table
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vehicle_id")
	private int vehicleId;
	@Column(name = "vehicle_make")
	private String vehicleMake;
	@Column(name = "vehicle_model")
	private String vehicleModel;
	@Column(name = "vehicle_number")
	@ColumnTransformer(read = "UPPER(vehicle_number)")
	private  String vehicleNumber;
	
	public Vehicle() {
		
	}

	public Vehicle(String vehicleMake, String vehicleModel, String vehicleNumber) {
		this.vehicleMake = vehicleMake;
		this.vehicleModel = vehicleModel;
		this.vehicleNumber = vehicleNumber;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleMake() {
		return vehicleMake;
	}

	public void setVehicleMake(String vehicleMake) {
		this.vehicleMake = vehicleMake;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleMake=" + vehicleMake + ", vehicleModel=" + vehicleModel
				+ ", vehicleNumber=" + vehicleNumber + "]";
	}
	
	public static Comparator<Vehicle> vehicleCoparator = new Comparator<Vehicle>() {

		@Override
		public int compare(Vehicle v1, Vehicle v2) {
			String vehilceMake1 = v1.getVehicleMake();
			String vehicleMake2 = v2.getVehicleMake();
			return vehilceMake1.compareTo(vehicleMake2);
		}
		
	};
}
