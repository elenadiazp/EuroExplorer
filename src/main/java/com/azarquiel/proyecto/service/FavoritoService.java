package com.azarquiel.proyecto.service;

import com.azarquiel.proyecto.entidades.Favorito;
import com.azarquiel.proyecto.entidades.Ruta;

import java.util.List;

public interface FavoritoService {
    void guardarQuitarFavorito(Long idRuta, Long idUsuario);
    boolean esFavorito(Long idRuta, Long idUsuario);
    List<Ruta> obtenerFavoritos(Long idUsuario);
}
