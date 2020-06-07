package com.zzpj.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Data
public class User {

    @Id
    @GeneratedValue(generator="uuid")
    @Column(columnDefinition = "uuid", updatable = false)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID uuid;

    @Column(unique = true)
    private String login;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Purchase> purchases;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "uuid"))
    private Collection<Role> roles;

}
