package com.azarquiel.proyecto.service;

import com.azarquiel.proyecto.entidades.Puntuacion;
import com.azarquiel.proyecto.entidades.Ruta;
import com.azarquiel.proyecto.entidades.Usuario;

import java.util.Optional;

public interface PuntuacionService {
    void guardarOPuntuar(Puntuacion puntuacion);
    Optional<Puntuacion>buscarPorUsuarioYRuta(Long idusuario, Long idRuta);
    Double  calcularPuntuacionMedia(Long idRuta);
}
