package com.azarquiel.proyecto.service;

import com.azarquiel.proyecto.daos.PaisRepository;
import com.azarquiel.proyecto.dto.PaisDto;
import com.azarquiel.proyecto.entidades.InfoPractica;
import com.azarquiel.proyecto.entidades.Pais;
import com.azarquiel.proyecto.entidades.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PaisServiceImplements  implements PaisService{
    private PaisRepository paisRepository;
    @Autowired
    public PaisServiceImplements(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }
    @Override
    public List<PaisDto> findAll() {
        List<Pais> paises = paisRepository.findAll(Sort.by(Sort.Order.asc("nombre")));
        List<PaisDto> paisDtoList = paises.stream().map(pais -> new PaisDto(pais.getId(),pais.getNombre(),pais.getUrlImagen())).toList();
        return paisDtoList;
    }

    @Override
    public PaisDto findById(Long id) {
    Pais pais= paisRepository.findById(id).orElse(null);
    Set<InfoPractica> infoPracticas= pais.getInfoPracticas();
    Set<Region> regions= pais.getRegions();
        return new PaisDto(pais.getId(), pais.getNombre(),pais.getInfo(), infoPracticas, regions);
    }

    @Override
    public List<PaisDto> buscarPorNombre(String query) {
        return paisRepository.findByNombreContainingIgnoreCase(query)
                .stream().map(pais -> new PaisDto(pais.getId(),pais.getNombre(),pais.getUrlImagen())).toList();
    }
}
