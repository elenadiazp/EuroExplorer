package com.azarquiel.proyecto.daos;

import com.azarquiel.proyecto.entidades.Favorito;
import com.azarquiel.proyecto.entidades.Ruta;
import com.azarquiel.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritoRepository extends JpaRepository<Favorito, Long>{
    Optional<Favorito> findByIdUsuarioAndIdRuta(Usuario idUsuario, Ruta idRuta);
    List<Favorito> findByIdUsuario_Id(Long id);

}

