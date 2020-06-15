package com.zzpj.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Data
public class Alcohol {

    @Id
    @GeneratedValue
    @Column( columnDefinition = "uuid", updatable = false )
    private UUID uuid;

    @Column(unique = true)
    @NotNull
    private String name;

    @Column
    @Min(0)
    private double cost;

    @OneToMany(mappedBy = "alcohol")
    @JsonIgnore
    private List<PurchaseList> purchaseLists = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "warehouse_uuid", referencedColumnName = "uuid")
    private Warehouse warehouse;
}
