package com.parqueadero.parking_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parking_api.entity.ParkingRecord;
import com.parqueadero.parking_api.entity.Vehicle;
import com.parqueadero.parking_api.enums.ParkingStatus;

import java.util.List;
import java.util.Optional;
public interface ParkingRecordRepository  extends JpaRepository<ParkingRecord, Long>{
	 Optional<ParkingRecord> findByVehicleAndStatus(
	            Vehicle vehicle,
	            ParkingStatus status
	    );

	    List<ParkingRecord> findByStatus(
	            ParkingStatus status
	    );
}
