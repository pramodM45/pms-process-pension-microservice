package com.example.processpensionmicroservice.feignproxy;

import com.example.processpensionmicroservice.exceptions.AadhaarNotFoundException;
import com.example.processpensionmicroservice.exceptions.JWTException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        if(response.status()==401){
            return new JWTException("invalid or expired jwt token");
        }
        if(response.status()==404){
            return new AadhaarNotFoundException("aadhaar number not found");
        }
        return null;
    }
}
