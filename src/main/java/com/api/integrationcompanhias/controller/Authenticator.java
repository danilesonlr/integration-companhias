package com.api.integrationcompanhias.controller;

import com.api.integrationcompanhias.service.AuthenticatorService;
import com.api.integrationcompanhias.util.UtilJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

@RestController
@RequestMapping("/v1")
public class Authenticator {
    @Autowired
    AuthenticatorService service;
    @GetMapping("/authenticate")
    public ResponseEntity authenticate(){
        return ResponseEntity.ok(UtilJSON.getObject(service.authenticate()));
    }
}
