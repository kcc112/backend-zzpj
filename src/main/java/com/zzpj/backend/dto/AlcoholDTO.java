package com.zzpj.backend.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class AlcoholDTO {
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private double cost;
    private int amount;
}