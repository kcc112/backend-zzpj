package com.zzpj.backend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class WarehouseDTO {

    private UUID uuid;
    private int amount;
}
