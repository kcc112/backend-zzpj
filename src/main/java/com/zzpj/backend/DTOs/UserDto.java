package com.zzpj.backend.DTOs;


import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {
    @Getter
    @NotNull
    @NotEmpty
    private String login;
    @Getter
    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
}
