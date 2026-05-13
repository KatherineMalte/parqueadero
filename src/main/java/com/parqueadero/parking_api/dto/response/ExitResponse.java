package com.parqueadero.parking_api.dto.response;
import lombok.*;

import java.math.BigDecimal;

import com.parqueadero.parking_api.enums.VehicleType;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExitResponse {
	 private String plate;
	    private VehicleType type;
	    private Long minutes;
	    private BigDecimal total;
}
