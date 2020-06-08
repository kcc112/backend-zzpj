package com.zzpj.backend.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class AddPurchaseDTO {
    private UUID userID;
    private List<AddPurchaseListDTO> purchaseList = new ArrayList<>();
}
