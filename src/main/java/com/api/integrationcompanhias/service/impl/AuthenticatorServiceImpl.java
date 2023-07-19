package com.api.integrationcompanhias.service.impl;

import com.api.integrationcompanhias.dto.AuthenticatorDto;
import com.api.integrationcompanhias.service.AuthenticatorService;
import com.api.integrationcompanhias.util.UtilJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthenticatorServiceImpl implements AuthenticatorService {
    @Value("${client}")
    private String client;
    @Value("${secret.client}")
    private String secretClient;
    @Value("${projuris.url.authenticate}")
    private String URL;

    @Autowired
    RestTemplate restTemplate;

    @Override
    @Cacheable("token")
    public String authenticate() {
        AuthenticatorDto dto = new AuthenticatorDto(client, secretClient);
        String token = restTemplate.postForObject(URL,dto, String.class);
        return token;
    }
}
