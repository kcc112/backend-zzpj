package com.zzpj.backend.repositories;

import com.zzpj.backend.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {
    Privilege findByName(String name);
}
