package com.azarquiel.proyecto.daos;

import com.azarquiel.proyecto.entidades.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    List<Pais> findByNombreContainingIgnoreCase(String query);
}
