package com.zzpj.backend.dto;

import com.zzpj.backend.entities.PurchaseList;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
public class GetAllPurchaseDTO {
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    private List<PurchaseList> purchaseLists;
    private UserLoginIdDTO user;

    public GetAllPurchaseDTO(UUID id){
        this.uuid = id;
    }
}
