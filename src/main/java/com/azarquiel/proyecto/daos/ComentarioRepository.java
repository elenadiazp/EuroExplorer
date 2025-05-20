package com.azarquiel.proyecto.daos;

import com.azarquiel.proyecto.entidades.Comentario;
import com.azarquiel.proyecto.entidades.Ruta;
import com.azarquiel.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    Optional<Comentario> findByIdUsuarioAndIdRuta(Usuario usuario, Ruta ruta);
    List<Comentario> findAllByIdRuta(Ruta ruta);
    Page<Comentario>findAllByIdRuta_IdOrderByFechaDesc(Long rutaId, Pageable pageable);
}
