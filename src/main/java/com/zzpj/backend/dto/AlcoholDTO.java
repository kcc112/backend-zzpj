package com.zzpj.backend.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.UUID;

@Data
public class AlcoholDTO {
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    private String name;
    private double cost;
    private int amount;
    private UUID warehouseUuid;
}