package com.zzpj.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    @Column
    private String password;
    @Column(updatable = false)
    private String type;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Purchase> purchases;

}
