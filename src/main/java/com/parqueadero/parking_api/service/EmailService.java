package com.parqueadero.parking_api.service;

import com.parqueadero.parking_api.entity.ParkingRecord;

public interface EmailService {
	 void sendExitEmail(ParkingRecord record);
}
