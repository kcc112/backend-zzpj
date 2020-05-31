package com.zzpj.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "warehouse")
    @JsonIgnore
    private Alcohol alcohol;
    @Column
    @Min(0)
    private int amount;

    public void addAmount(int amount){
        this.amount += amount;
    }
}
