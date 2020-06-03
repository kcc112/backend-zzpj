package com.zzpj.backend.DTOs;


import com.zzpj.backend.validation.PasswordMatches;
import com.zzpj.backend.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    @ValidEmail
    private String login;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;
}
