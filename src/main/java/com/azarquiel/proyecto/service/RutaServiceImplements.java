package com.azarquiel.proyecto.service;

import com.azarquiel.proyecto.daos.RutaRepository;
import com.azarquiel.proyecto.dto.RutaDto;
import com.azarquiel.proyecto.entidades.Ruta;
import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RutaServiceImplements implements RutaService {
    private RutaRepository rutaRepository;
    @Autowired
    public RutaServiceImplements(RutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }


    @Override
    public List<RutaDto> findByPaisId(Long paisid) {
        List<Ruta>rutas=rutaRepository.findByPaisId(paisid);
        return rutas.stream().map(ruta -> new RutaDto(ruta.getId(), ruta.getIdtiporuta(), ruta.getIdregion(), ruta.getFoto1(),ruta.getNombre())).toList();
    }

    @Override
    public RutaDto findById(Long id) {

        Optional<Ruta>optionalruta = rutaRepository.findById(id);
        if(optionalruta.isPresent()){
            Ruta ruta=optionalruta.get();
            return new RutaDto(ruta.getId(),ruta.getIdtiporuta(),ruta.getIdregion(),ruta.getFoto1(),ruta.getFoto2() ,ruta.getNombre(), ruta.getIntinerario(),ruta.getDireccion(),ruta.getOtrosDatos(),ruta.getComentarios(), ruta.getFavoritos(), ruta.getPuntuacions());
        }else {
            return null;
        }

    }

    @Override
    public Page<RutaDto> findByTipo(String tipo, Pageable pageable) {
      Page<Ruta> rutas= rutaRepository.findByIdtiporutaNombreIgnoreCase(tipo, pageable);
        return rutas.map(ruta -> new RutaDto(ruta.getId(), ruta.getIdtiporuta(), ruta.getIdregion(), ruta.getFoto1(),ruta.getNombre(),ruta.getIdregion().getIdPais().getNombre()));
    }

    @Override
    public List<RutaDto> rutasAleatoriasPorTipo(String tipo) {
        List<Ruta> rutas= rutaRepository.findByIdtiporutaNombreIgnoreCase(tipo);
        Collections.shuffle(rutas);//para obtener orden aleatorio
        return rutas.stream().limit(3).map(ruta -> new RutaDto(ruta.getId(),
                ruta.getIdtiporuta(), ruta.getIdregion(), ruta.getFoto1(),ruta.getNombre(),
                ruta.getIdregion().getIdPais().getNombre())).toList();
    }

    @Override
    public List<Ruta> buscarRutas(Long paisId, List<Long> tipoRutaIds, List<Long> regionsIds) {
        return rutaRepository.findByFiltros(paisId, tipoRutaIds, regionsIds);
    }

    @Override
    public List<RutaDto> obtenerTop3MejorValoradas() {
        Pageable top3 = PageRequest.of(0,3);
        Page<Ruta> rutasPage = rutaRepository.findTopRutasByPuntuacionMedia(top3);

        return rutasPage.stream().map(ruta -> new RutaDto(ruta.getId(),ruta.getIdtiporuta(),
                ruta.getIdregion(),ruta.getFoto1(), ruta.getNombre(),
                ruta.getIdregion().getIdPais().getNombre())).toList();
    }


}
