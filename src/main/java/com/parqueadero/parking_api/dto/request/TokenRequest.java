package com.parqueadero.parking_api.dto.request;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenRequest {
	 private String username;
	 private String password;
}
