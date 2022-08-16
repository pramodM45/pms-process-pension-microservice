package com.example.processpensionmicroservice.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTValidateResponse {
    private boolean isValid;
    private String username;
    private String message;
}
