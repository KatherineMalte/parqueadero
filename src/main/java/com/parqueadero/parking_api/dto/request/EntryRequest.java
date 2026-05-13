package com.parqueadero.parking_api.dto.request;
import com.parqueadero.parking_api.enums.VehicleType;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntryRequest {
	private String plate;
    private VehicleType type;
}
