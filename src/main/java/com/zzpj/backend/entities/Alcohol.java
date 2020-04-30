package com.zzpj.backend.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
public class Alcohol {

    @Id
    private UUID id;
    @Column(unique = true)
    private String name;
    @Column
    @Min(0)
    private double cost;
    @Column
    @Min(0)
    private int amount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
