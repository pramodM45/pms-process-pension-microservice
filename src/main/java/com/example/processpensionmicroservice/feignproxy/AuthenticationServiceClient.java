package com.example.processpensionmicroservice.feignproxy;

import com.example.processpensionmicroservice.model.JWTValidateRequest;
import com.example.processpensionmicroservice.model.JWTValidateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "authentication-service",url = "${AUTH-SERVICE:http://localhost:8080}/authentication-service")
public interface AuthenticationServiceClient {

    @PostMapping("/validate")
    public ResponseEntity<JWTValidateResponse> validateToken(JWTValidateRequest request);
}
