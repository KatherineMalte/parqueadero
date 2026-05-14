package com.parqueadero.parking_api.service.impl;

import com.parqueadero.parking_api.dto.request.EntryRequest;
import com.parqueadero.parking_api.dto.response.ExitResponse;
import com.parqueadero.parking_api.entity.ParkingRecord;
import com.parqueadero.parking_api.entity.Vehicle;
import com.parqueadero.parking_api.enums.ParkingStatus;
import com.parqueadero.parking_api.repository.ParkingRecordRepository;
import com.parqueadero.parking_api.repository.VehicleRepository;
import com.parqueadero.parking_api.service.EmailService;
import com.parqueadero.parking_api.service.ParkingService;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService{
	 private final VehicleRepository vehicleRepository;
	    private final ParkingRecordRepository parkingRecordRepository;
	    private final EmailService emailService;
	    private static final BigDecimal RATE_PER_MINUTE =
	            BigDecimal.valueOf(50);

		@Override
		public ParkingRecord registerEntry(EntryRequest request) {
			 Vehicle vehicle = vehicleRepository.findByPlate(request.getPlate())
		                .orElseGet(() ->
		                        vehicleRepository.save(
		                                Vehicle.builder()
		                                        .plate(request.getPlate())
		                                        .type(request.getType())
		                                        .build()
		                        )
		                );

		        parkingRecordRepository
		                .findByVehicleAndStatus(vehicle, ParkingStatus.ACTIVE)
		                .ifPresent(r -> {
		                    throw new RuntimeException("Vehicle already active");
		                });

		        ParkingRecord record = ParkingRecord.builder()
		                .vehicle(vehicle)
		                .status(ParkingStatus.ACTIVE)
		                .build();

		        return parkingRecordRepository.save(record);
		}

		@Override
		public ExitResponse registerExit(String plate) {
			Vehicle vehicle = vehicleRepository.findByPlate(plate)
	                .orElseThrow(() ->
	                        new RuntimeException("Vehicle not found"));

	        ParkingRecord record = parkingRecordRepository
	                .findByVehicleAndStatus(vehicle, ParkingStatus.ACTIVE)
	                .orElseThrow(() ->
	                        new RuntimeException("Vehicle not active"));

	        LocalDateTime exitTime = LocalDateTime.now();

	        long minutes = Duration
	                .between(record.getEntryTime(), exitTime)
	                .toMinutes();

	        minutes = Math.max(minutes, 1);

	        BigDecimal total =
	                RATE_PER_MINUTE.multiply(
	                        BigDecimal.valueOf(minutes)
	                );

	        record.setExitTime(exitTime);
	        record.setTotalMinutes(minutes);
	        record.setTotalAmount(total);
	        record.setStatus(ParkingStatus.EXITED);
	        try {
	            emailService.sendExitEmail(record);
	            System.out.println("MAIL OK");
	            record.setEmailSent(true);
	        } catch (Exception e) {
	        	System.out.println("ERROR" + e.getMessage());
	            record.setEmailSent(false);
	        }
	        parkingRecordRepository.save(record);
	        System.out.println("GUARDADO");

	        return ExitResponse.builder()
	                .plate(vehicle.getPlate())
	                .type(vehicle.getType())
	                .minutes(minutes)
	                .total(total)
	                .build();
		}

		@Override
		public List<ParkingRecord> getActiveVehicles() {
			 return parkingRecordRepository
		                .findByStatus(ParkingStatus.ACTIVE);
		}
}
