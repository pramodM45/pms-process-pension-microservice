package com.example.processpensionmicroservice.controller;

import com.example.processpensionmicroservice.model.PensionDetail;
import com.example.processpensionmicroservice.model.PensionerDetail;
import com.example.processpensionmicroservice.model.ProcessPensionInput;
import com.example.processpensionmicroservice.service.ProcessPensionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProcessPensionController.class)
class ProcessPensionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProcessPensionService processPensionService;
    @Test
    void getPensionDetails() throws Exception {
        when(processPensionService.validateUser(anyString())).thenReturn(true);
        when(processPensionService.getPensioner(anyString(),anyString()))
                .thenReturn(PensionerDetail.builder().id(1000L).build());
        when(processPensionService.getPensionDetails(any(PensionerDetail.class)))
                .thenReturn(PensionDetail.builder().pensionAmount(BigDecimal.ONE).build());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","test_token");

        RequestBuilder builder = MockMvcRequestBuilders.post("/ProcessPension")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"aadhaarNumber\":\"test_aadhaarnumber\"}");

        MvcResult mvcResult = mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andReturn();

    }
}