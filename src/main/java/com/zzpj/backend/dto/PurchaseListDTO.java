package com.zzpj.backend.dto;

import com.zzpj.backend.entities.Alcohol;
import com.zzpj.backend.entities.Purchase;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.UUID;

@Data
public class PurchaseListDTO {
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    private Alcohol alcohol;
    private Purchase purchase;
    private int buyAmount;
}