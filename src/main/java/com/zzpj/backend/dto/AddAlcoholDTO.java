package com.zzpj.backend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AddAlcoholDTO {

    private UUID uuid;
    private String name;
    private double cost;
    private UUID warehouseUuid;
}
