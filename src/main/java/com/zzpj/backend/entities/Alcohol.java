package com.zzpj.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Alcohol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column
    @Min(0)
    private double cost;
    @Column
    @Min(0)
    private int amount;
    @OneToMany(mappedBy = "alcohol")
    @JsonIgnore
    private List<PurchaseAlcohol> purchaseAlcohols = new ArrayList<>();
}
