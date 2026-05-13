package com.parqueadero.parking_api.service;
import java.util.List;

import com.parqueadero.parking_api.dto.request.EntryRequest;
import com.parqueadero.parking_api.dto.response.ExitResponse;
import com.parqueadero.parking_api.entity.ParkingRecord;
public interface  ParkingService {
	 ParkingRecord registerEntry(EntryRequest request);

	    ExitResponse registerExit(String plate);

	    List<ParkingRecord> getActiveVehicles();
}
