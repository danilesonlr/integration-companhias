package com.api.integrationcompanhias.controller;

import com.api.integrationcompanhias.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping("/v1")
public class Integration<T> {
    @Autowired
    private IntegrationService integrationService;

    @GetMapping("/integration")
    public ResponseEntity integration(){
        return ResponseEntity.ok(integrationService.entidade());
    }
}
