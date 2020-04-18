package org.home.ledger.service;

import java.util.ArrayList;
import java.util.List;

import org.home.ledger.model.Vehicle;
import org.home.ledger.model.VehicleSpend;
import org.home.ledger.repository.VehicleRepository;
import org.home.ledger.repository.VehicleSpendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
	@Autowired
	VehicleRepository vehicleRepository;
	@Autowired
	VehicleSpendRepository vehicleSpendRepository;
	public List<Vehicle> getVehicles() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		vehicleRepository.findAll().forEach(v -> vehicles.add(v));
		return vehicles;
	}
	public Vehicle getVehicle(String vehicleNumber) {
		return vehicleRepository.findByVehicleNumber(vehicleNumber);
	}
	public void addVehilce(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}
	public List<VehicleSpend> getVehicleSpends() {
		List<VehicleSpend> vehicleSpends = new ArrayList<VehicleSpend>();
		vehicleSpendRepository.findAll().forEach(vs -> vehicleSpends.add(vs));
		return vehicleSpends;
	}
	public VehicleSpend getVehicleSpend(String spendName) {
		return vehicleSpendRepository.findBySpendName(spendName);
	}
	public void addVehilceSpend(VehicleSpend vehicleSpend) {
		vehicleSpendRepository.save(vehicleSpend);		
	}
	public Vehicle getVehicle(int vehicleId) {
		return vehicleRepository.findById(vehicleId).get();
	}
	public VehicleSpend getVehicleSpend(int spendId) {
		return vehicleSpendRepository.findById(spendId).get();
	}
	public void deleteVehicle(int vehicleId) {
		vehicleRepository.deleteById(vehicleId);
	}
	public void deleteVehicleSpend(int spendId) {
		vehicleSpendRepository.deleteById(spendId);		
	}
}
