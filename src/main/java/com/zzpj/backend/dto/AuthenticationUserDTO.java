package com.zzpj.backend.dto;

import com.zzpj.backend.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AuthenticationUserDTO {
    @NotNull
    @NotEmpty
    @ValidEmail
    private String login;

    @NotNull
    @NotEmpty
    private String password;

    public AuthenticationUserDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
