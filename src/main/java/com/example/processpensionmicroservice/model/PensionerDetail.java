package com.example.processpensionmicroservice.model;

import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PensionerDetail {
    private Long id;
    private String aadhaarNumber;
    private String name;
    private LocalDate dateOfBirth;
    private String PAN;
    private BigDecimal SalaryEarned;
    private BigDecimal allowences;
    private PensionType pensionType;
    private BankDetails bankDetails;
}
