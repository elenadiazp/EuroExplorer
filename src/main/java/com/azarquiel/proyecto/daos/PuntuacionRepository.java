package com.azarquiel.proyecto.daos;

import com.azarquiel.proyecto.entidades.Favorito;
import com.azarquiel.proyecto.entidades.Puntuacion;
import com.azarquiel.proyecto.entidades.Ruta;
import com.azarquiel.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {
Optional<Puntuacion> findByIdUsuario_IdAndIdRuta_Id(Long idUsuario, Long idRuta);
List<Puntuacion> findByIdRuta_Id(Long idRuta);

}
