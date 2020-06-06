package com.zzpj.backend.repositories;

import com.zzpj.backend.entities.Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol, UUID> {
}
