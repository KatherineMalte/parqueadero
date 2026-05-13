package com.parqueadero.parking_api.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parqueadero.parking_api.dto.request.EntryRequest;
import com.parqueadero.parking_api.dto.response.ExitResponse;
import com.parqueadero.parking_api.entity.ParkingRecord;
import com.parqueadero.parking_api.service.ParkingService;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ParkingController {
	 private final ParkingService parkingService;

	    @PostMapping("/entry")
	    public ResponseEntity<ParkingRecord> registerEntry(
	            @RequestBody EntryRequest request
	    ) {
	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(parkingService.registerEntry(request));
	    }

	    @PostMapping("/exit/{plate}")
	    public ResponseEntity<ExitResponse> registerExit(
	            @PathVariable String plate
	    ) {
	        return ResponseEntity.ok(
	                parkingService.registerExit(plate)
	        );
	    }

	    @GetMapping("/active")
	    public ResponseEntity<List<ParkingRecord>> getActiveVehicles() {
	        return ResponseEntity.ok(
	                parkingService.getActiveVehicles()
	        );
	    }
}
