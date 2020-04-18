package org.home.ledger.repository;

import org.home.ledger.model.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
	public Vehicle findByVehicleNumber(String vehicleNumber);
}
