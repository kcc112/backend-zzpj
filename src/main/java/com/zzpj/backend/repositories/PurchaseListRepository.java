package com.zzpj.backend.repositories;

import com.zzpj.backend.entities.PurchaseList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseListRepository extends JpaRepository<PurchaseList, Long> {
}
