package com.api.integrationcompanhias.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;

public class UtilJSON {


    public static Map<String, String> getObject(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        // Converte o JSON em um Map
        try {
            Map<String, String> jsonMap = objectMapper.readValue(jsonString, new TypeReference<Map<String, String>>() {
            });
            return jsonMap;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

