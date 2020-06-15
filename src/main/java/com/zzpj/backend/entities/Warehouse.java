package com.zzpj.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(generator="uuid")
    @Column(columnDefinition = "uuid", updatable = false)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Getter
    @Setter
    private UUID uuid;

    @OneToMany(mappedBy = "warehouse")
    @Getter
    @Setter
    @JsonIgnore
    private List<Alcohol> alcohols;

    @Column
    @Min(0)
    @Getter
    @Setter
    private int amount;

    public void addAmount(int amount){
        this.amount += amount;
    }

}
