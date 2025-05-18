package com.azarquiel.proyecto.service;

import com.azarquiel.proyecto.dto.PaisDto;


import java.util.List;

public interface PaisService {
    List<PaisDto> findAll();
    PaisDto findById(Long id);
    List<PaisDto>buscarPorNombre(String nombre);
}
