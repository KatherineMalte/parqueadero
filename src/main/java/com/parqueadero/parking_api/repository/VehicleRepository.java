package com.parqueadero.parking_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parking_api.entity.Vehicle;

import java.util.Optional;
public interface VehicleRepository  extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByPlate(String plate);

}
