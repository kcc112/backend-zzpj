package com.zzpj.backend.dto;

import com.zzpj.backend.entities.PurchaseList;
import com.zzpj.backend.entities.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class PurchaseDTO {
    @Setter(AccessLevel.NONE)
    private Long id;
    private List<PurchaseList> purchaseLists;
    private User user;
}