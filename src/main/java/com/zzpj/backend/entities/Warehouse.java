package com.zzpj.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @OneToOne(mappedBy = "warehouse")
    @JsonIgnore
    @Getter
    @Setter
    private Alcohol alcohol;
    @Column
    @Min(0)
    @Getter
    @Setter
    private int amount;

    public void addAmount(int amount){
        this.amount += amount;
    }
}
