package com.azarquiel.proyecto.service;

import com.azarquiel.proyecto.dto.RutaDto;
import com.azarquiel.proyecto.entidades.Ruta;
import org.springframework.data.domain.Page;


import org.springframework.data.domain.Pageable;
import java.util.List;

public interface RutaService {
List<RutaDto> findByPaisId(Long paisid);
RutaDto findById(Long id);
Page<RutaDto> findByTipo(String tipo, Pageable pageable);//rutas tipo paginado
List<RutaDto> rutasAleatoriasPorTipo(String tipo);
List<Ruta> buscarRutas(Long paisId, List<Long>tipoRutaIds, List<Long> regionsIds);
List<RutaDto> obtenerTop3MejorValoradas();
}
