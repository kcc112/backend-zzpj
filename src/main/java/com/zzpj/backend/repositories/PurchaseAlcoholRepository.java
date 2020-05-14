package com.zzpj.backend.repositories;

import com.zzpj.backend.entities.PurchaseAlcohol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseAlcoholRepository extends JpaRepository<PurchaseAlcohol, Long> {
}
