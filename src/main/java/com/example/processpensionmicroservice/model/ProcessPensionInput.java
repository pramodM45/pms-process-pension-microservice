package com.example.processpensionmicroservice.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessPensionInput {
    private String aadhaarNumber;
}
