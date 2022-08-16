package com.example.processpensionmicroservice.controller;

import com.example.processpensionmicroservice.model.PensionDetail;
import com.example.processpensionmicroservice.model.PensionerDetail;
import com.example.processpensionmicroservice.model.ProcessPensionInput;
import com.example.processpensionmicroservice.service.ProcessPensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ProcessPensionController {

    @Autowired
    ProcessPensionService processPensionService;

    @PostMapping("/ProcessPension")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<PensionDetail> getPensionDetails(@RequestBody ProcessPensionInput processPensionInput,
                                                           @RequestHeader("Authorization") String token){
        boolean isValid = processPensionService.validateUser(token);
        if(isValid){
            PensionerDetail pensionerDetail = processPensionService.getPensioner(processPensionInput.getAadhaarNumber(),token);
            PensionDetail pensionDetail = processPensionService.getPensionDetails(pensionerDetail);
            return new ResponseEntity<>(pensionDetail, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck(){
        HashMap<String,String> map = new HashMap<>();
        map.put("Status","UP");
        return ResponseEntity.ok(map);
    }
}
