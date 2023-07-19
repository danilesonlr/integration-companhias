package com.api.integrationcompanhias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.management.ConstructorParameters;

@Data
@AllArgsConstructor
public class AuthenticatorDto {
    public String username;
    public String password;
}
