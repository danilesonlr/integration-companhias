package com.api.integrationcompanhias.service.impl;

import com.api.integrationcompanhias.service.AuthenticatorService;
import com.api.integrationcompanhias.service.IntegrationService;
import com.api.integrationcompanhias.util.UtilJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class IntegrationServiceImpl implements IntegrationService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthenticatorService authenticatorService;

    @Value("${projuris.url.entidade}")
    private String URL;


    @Override
    public String entidade() {

        Map<String, String> tokenMap = UtilJSON.getObject(authenticatorService.authenticate());

        // Define o token no header da requisição
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + tokenMap.get("access_token"));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Faz a chamada GET à API
        ResponseEntity responseBody  = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

        return responseBody.getBody().toString();
    }
}
