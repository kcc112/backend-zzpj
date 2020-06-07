package com.zzpj.backend.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.UUID;

@Data
public class UserLoginIdDTO {

    @Setter(AccessLevel.NONE)
    UUID id;
    String login;

    public UserLoginIdDTO(UUID id){
        this.id = id;
    }
}
