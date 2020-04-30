package com.zzpj.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;


@Entity
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
//    @ManyToMany(mappedBy = "alcohols")
//    private List<Purchase> purchases;
//
//
//    public List<Purchase> getPurchases() {
//        return purchases;
//    }
//
//    public void setPurchases(List<Purchase> purchases) {
//        this.purchases = purchases;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
