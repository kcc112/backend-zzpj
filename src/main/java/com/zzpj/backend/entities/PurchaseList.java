package com.zzpj.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Entity(name ="purchase_list")
@Data
public class PurchaseList {

    @Id
    @GeneratedValue(generator="uuid")
    @Column(columnDefinition = "uuid", updatable = false)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "alcohol_uuid", referencedColumnName = "uuid")
    private Alcohol alcohol;

    @ManyToOne
    @JoinColumn(name = "purchase_uuid", referencedColumnName = "uuid")

    @JsonIgnore
    private Purchase purchase;

    @Column(name = "buy_amount")
    private int buyAmount;
}
