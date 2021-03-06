package com.zzpj.backend.dto;

import com.zzpj.backend.entities.PurchaseList;
import com.zzpj.backend.entities.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
public class PurchaseDTO {
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    private List<PurchaseList> purchaseLists;
    private User user;
}