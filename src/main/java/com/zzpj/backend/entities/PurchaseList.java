package com.zzpj.backend.entities;

import lombok.Data;

import javax.persistence.*;

@Entity(name ="purchase_list")
@Data
public class PurchaseList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "alcohol_id", referencedColumnName = "id")
    private Alcohol alcohol;
    @ManyToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "id")
    private Purchase purchase;
    @Column(name = "buy_amount")
    private int buyAmount;
}
