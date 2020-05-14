package com.zzpj.backend.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PurchaseAlcohol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "alcohol_id", referencedColumnName = "id")
    private Alcohol alcohol;
    @ManyToOne
    @JoinColumn(name = "purchase_list_id", referencedColumnName = "id")
    private PurchaseList purchaseList;
    @Column(name = "buy_amount")
    private int buyAmount;
}
