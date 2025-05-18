package com.azarquiel.proyecto.service;

import com.azarquiel.proyecto.daos.PuntuacionRepository;
import com.azarquiel.proyecto.entidades.Puntuacion;
import com.azarquiel.proyecto.entidades.Ruta;
import com.azarquiel.proyecto.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PuntuacionServiceImplements implements PuntuacionService{
    @Autowired
    private PuntuacionRepository puntuacionRepository;

public PuntuacionServiceImplements(PuntuacionRepository puntuacionRepository) {
    this.puntuacionRepository = puntuacionRepository;
}

    @Override
    public void guardarOPuntuar(Puntuacion nuevaPuntuacion) {
        Long idUsuario = nuevaPuntuacion.getIdUsuario().getId();
        Long idRuta= nuevaPuntuacion.getIdRuta().getId();
        Optional<Puntuacion> existente= puntuacionRepository.findByIdUsuario_IdAndIdRuta_Id(idUsuario, idRuta);
        if(existente.isPresent()){
            Puntuacion puntuacionExistente= existente.get();
            puntuacionExistente.setPuntuacion(nuevaPuntuacion.getPuntuacion());
            puntuacionRepository.save(puntuacionExistente);
        }else{
            puntuacionRepository.save(nuevaPuntuacion);
        }

    }

    @Override
    public Optional<Puntuacion> buscarPorUsuarioYRuta(Long idUsuario, Long idRuta) {
        return puntuacionRepository.findByIdUsuario_IdAndIdRuta_Id(idUsuario, idRuta);
    }

    @Override
    public Double calcularPuntuacionMedia(Long idRuta) {
        List<Puntuacion> puntuaciones = puntuacionRepository.findByIdRuta_Id(idRuta);
        if(puntuaciones.isEmpty()){
            return 0.0;
        }
        return puntuaciones.stream().mapToDouble(Puntuacion::getPuntuacion).average().orElse(0.0);
    }
}
