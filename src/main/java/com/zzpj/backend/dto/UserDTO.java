package com.zzpj.backend.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.UUID;

@Data
public class UserDTO {
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    private String login;
    private String password;
    private String type;
}