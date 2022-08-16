package com.example.processpensionmicroservice.service;

import com.example.processpensionmicroservice.feignproxy.AuthenticationServiceClient;
import com.example.processpensionmicroservice.feignproxy.PensionerDetailsServiceClient;
import com.example.processpensionmicroservice.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class ProcessPensionService {

    @Autowired
    AuthenticationServiceClient authClient;

    @Autowired
    PensionerDetailsServiceClient pensionerDetailsServiceClient;

    public boolean validateUser(String token){
        ResponseEntity<JWTValidateResponse> responseEntity = authClient.validateToken(new JWTValidateRequest(token));
        System.out.println(responseEntity.getBody().getMessage());
        return responseEntity.getBody().isValid();
    }

    public PensionerDetail getPensioner(String aadhaarNumber,String token) {
        log.info("the aadhaar number is "+aadhaarNumber);
        ResponseEntity<PensionerDetail> pensionerDetails = pensionerDetailsServiceClient.getPensionerDetails(token, aadhaarNumber);
        log.info(pensionerDetails.toString());
        return pensionerDetails.getBody();
    }

    public PensionDetail getPensionDetails(PensionerDetail pensionerDetail) {
        BigDecimal pensionAmount = pensionerDetail.getSalaryEarned();
        if(pensionerDetail.getPensionType().equals(PensionType.SELF)){
            pensionAmount = pensionAmount.multiply(BigDecimal.valueOf(0.8));
        }
        if(pensionerDetail.getPensionType().equals(PensionType.FAMILY)){
            pensionAmount = pensionAmount.multiply(BigDecimal.valueOf(0.5));
        }

        BigDecimal bankServiceCharge = BigDecimal.valueOf(0);
        BankDetails bankDetails = pensionerDetail.getBankDetails();
        if(bankDetails.getBankType().equals(BankType.PRIVATE)){
            bankServiceCharge=BigDecimal.valueOf(550);
        }
        if(bankDetails.getBankType().equals(BankType.PUBLIC)){
            bankServiceCharge= BigDecimal.valueOf(500);
        }

        return PensionDetail.builder()
                .pensionAmount(pensionAmount)
                .bankServiceCharge(bankServiceCharge).build();
    }
}
