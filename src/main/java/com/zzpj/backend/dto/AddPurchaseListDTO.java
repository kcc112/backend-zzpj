package com.zzpj.backend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AddPurchaseListDTO {
    private UUID alcoholID;
    private int buyAmount;
}
