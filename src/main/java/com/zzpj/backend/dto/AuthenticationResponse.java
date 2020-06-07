package com.zzpj.backend.dto;

import lombok.Getter;

public class AuthenticationResponse {

    @Getter
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
