package com.zzpj.backend.DTOs;

import com.zzpj.backend.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AuthenticationUserDto {
    @NotNull
    @NotEmpty
    @ValidEmail
    private String login;

    @NotNull
    @NotEmpty
    private String password;

    public AuthenticationUserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
