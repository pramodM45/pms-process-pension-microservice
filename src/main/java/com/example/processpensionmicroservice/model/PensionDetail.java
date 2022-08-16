package com.example.processpensionmicroservice.model;


import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PensionDetail {
    private BigDecimal pensionAmount;
    private BigDecimal bankServiceCharge;
}
