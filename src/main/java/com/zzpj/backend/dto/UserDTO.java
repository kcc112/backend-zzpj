package com.zzpj.backend.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class UserDTO {
    @Setter(AccessLevel.NONE)
    private Long id;
    private String login;
    private String password;
    private String type;
}