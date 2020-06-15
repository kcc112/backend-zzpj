package com.zzpj.backend.dto;


import lombok.Getter;
import lombok.Setter;

public class UserInfoDTO {

    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;
}
