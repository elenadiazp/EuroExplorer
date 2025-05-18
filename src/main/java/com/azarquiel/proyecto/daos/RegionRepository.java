package com.azarquiel.proyecto.daos;

import com.azarquiel.proyecto.entidades.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
