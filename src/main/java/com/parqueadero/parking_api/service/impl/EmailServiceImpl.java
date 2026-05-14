package com.parqueadero.parking_api.service.impl;

import com.parqueadero.parking_api.dto.request.EmailRequest;
import com.parqueadero.parking_api.entity.ParkingRecord;
import com.parqueadero.parking_api.service.AuthService;
import com.parqueadero.parking_api.service.EmailService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
private final WebClient webClient;
private final AuthService authService;
@Override
	public void sendExitEmail(ParkingRecord record) {
		// TODO Auto-generated method stub
	  

	        String token = authService.getToken();

	        String message = "Vehículo " + record.getVehicle().getPlate()
	                + " salió del parqueadero. Tiempo total: "
	                + record.getTotalMinutes()
	                + " minutos. Valor pagado: $"
	                + record.getTotalAmount();

	        EmailRequest request = EmailRequest.builder()

	                .configParams(
	                        EmailRequest.ConfigParams.builder()
	                                .idUser("parking-api")
	                                .idMessage("MSG-" + System.currentTimeMillis())
	                                .build()
	                )

	                .receivers(
	                        EmailRequest.Receivers.builder()
	                                .emailOrigen("katherine1441@hotmail.com")
	                                .to(List.of("katherinemalte73@gmail.com"))
	                                .copyTo(List.of("hierzuh@gmail.com"))
	                                .hiddenCopyTo(List.of())
	                                .build()
	                )

	                .email(
	                        EmailRequest.Email.builder()
	                                .subject("Salida registrada - Parqueadero")
	                                .urlHeader("")
	                                .urlFooter("")
	                                .message(message)
	                                .url_files(List.of())
	                                .build()
	                )

	                .build();

	        webClient.post()
	                .uri("https://dev-sites.similtech.co/api-email/api/email/sendEmail")
	                .header("Authorization", "Bearer " + token)
	                .bodyValue(request)
	                .retrieve()
	                .bodyToMono(String.class)
	                .block();
	    }

}
