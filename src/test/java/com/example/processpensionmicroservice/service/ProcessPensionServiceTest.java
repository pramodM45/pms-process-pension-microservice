package com.example.processpensionmicroservice.service;

import com.example.processpensionmicroservice.feignproxy.AuthenticationServiceClient;
import com.example.processpensionmicroservice.feignproxy.PensionerDetailsServiceClient;
import com.example.processpensionmicroservice.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class ProcessPensionServiceTest {

    @Mock
    AuthenticationServiceClient authClient;

    @Mock
    PensionerDetailsServiceClient pensionerDetailsServiceClient;

    @InjectMocks
    ProcessPensionService processPensionService;

    @Test
    void validateUser() {
        when(authClient.validateToken(any(JWTValidateRequest.class))).thenReturn(
                ResponseEntity.ok(JWTValidateResponse.builder().isValid(true).build())
        );
        boolean isValid = processPensionService.validateUser("abcde");
        log.info(String.valueOf(isValid));
        assertThat(isValid).isTrue();
    }

    @Test
    void getPensioner() {
        BankDetails bank = BankDetails.builder().bankType(BankType.PRIVATE).build();
        when(pensionerDetailsServiceClient.getPensionerDetails(anyString(),anyString())).thenReturn(
                ResponseEntity.ok(PensionerDetail.builder().bankDetails(bank).pensionType(PensionType.SELF).build())
        );
        PensionerDetail pd = processPensionService.getPensioner("1234","123");
//        log.info(pd.toString());
        assertThat(pd.getPensionType()).isEqualTo(PensionType.SELF);
        assertThat(pd.getBankDetails().getBankType()).isEqualTo(BankType.PRIVATE);

    }

    @Test
    void getPensionDetails() {
        BankDetails bank = BankDetails.builder().bankType(BankType.PRIVATE).build();
        PensionerDetail pd = PensionerDetail.builder().pensionType(PensionType.SELF)
                .SalaryEarned(new BigDecimal(1000L)).bankDetails(bank).build();
        PensionDetail pde = processPensionService.getPensionDetails(pd);
        assertThat(pde.getPensionAmount()).isEqualTo(new BigDecimal("800.0"));
        assertThat(pde.getBankServiceCharge()).isEqualTo(new BigDecimal(550L));
    }
}