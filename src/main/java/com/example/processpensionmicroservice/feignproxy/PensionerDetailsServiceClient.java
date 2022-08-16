package com.example.processpensionmicroservice.feignproxy;


import com.example.processpensionmicroservice.model.PensionerDetail;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "pensioner-details-service",url = "${AUTH-SERVICE:http://localhost:8081}/pensioner-details-service")
public interface PensionerDetailsServiceClient {


    @GetMapping("/PensionerDetailByAadhaar/{aadhaarNumber}")
    ResponseEntity<PensionerDetail> getPensionerDetails(@RequestHeader("Authorization") String token, @PathVariable("aadhaarNumber") String aadhaarNumber);
}
