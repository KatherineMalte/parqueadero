package com.parqueadero.parking_api.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.parqueadero.parking_api.dto.request.TokenRequest;
import com.parqueadero.parking_api.dto.response.TokenResponse;
import com.parqueadero.parking_api.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final WebClient webClient;

    @Override
    public String getToken() {

        TokenResponse response = webClient.post()
                .uri("https://dev-sites.similtech.co/api-email/api/token")
                .bodyValue(
                        TokenRequest.builder()
                                .username("proceso_pruebas")
                                .password("das487d32_*")
                                .build()
                )
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .block();

        return response.getToken();
    }
}
