package com.zzpj.backend.repositories;

import com.zzpj.backend.entities.Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol, Long> {

}
