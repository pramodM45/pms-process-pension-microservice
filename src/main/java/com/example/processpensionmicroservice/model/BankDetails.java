package com.example.processpensionmicroservice.model;

import lombok.*;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankDetails {

    private Long id;
    private String bankName;
    private String accountNumber;
    private BankType bankType;
}
